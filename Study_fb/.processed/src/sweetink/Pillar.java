package sweetink;
import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;


public class Pillar {
	int x, y1 = 0,y2;//柱子的x 和y
	int y1_h,y2_h;
	Image img1 ,img2;
	int width;
	int height;
	int screen_height;
	int screen_width;
	Random r = new Random();
	public Pillar(int x,Canvas c) throws Exception{
		img1 = Image.createImage("/img/item/Copy of down.png");
		img2 = Image.createImage("/img/item/Copy of up.png");
		System.out.println("屏幕高:"+c.getHeight()/2);
		float y = img1.getHeight();
		float rate =( c.getHeight())/y; //比例
		System.out.println("Rate:"+rate);
		System.out.println("new x:"+img1.getWidth()*rate);
		img1 = ImageUtil.scale(img1, img1.getWidth(), c.getHeight()); //缩放
		System.out.println("IMG_W:"+img1.getWidth()+",H:"+img1.getHeight());
		img2 = ImageUtil.scale(img2, img2.getWidth(), c.getHeight());
		width = img1.getWidth();
		this.screen_height = c.getHeight();
		this.screen_width = c.getWidth();
		height = img2.getHeight();
		this.x = x;//柱子x坐标相同
		this.y2 = r.nextInt(80)+120;//下方柱子的y
		this.y2_h = screen_height - y2;//下方柱子高度
		this.y1_h = screen_height - y2_h - 75; //上方柱子高度
		this.y1 = y2  - height - 75; //上方柱头 y
	}
	
	
	//实习柱子移动
	public void step(){
		x-=1;
		//System.out.println("当前x:"+x);
		if(x<=-40){
			
			x = screen_width;
			
			this.y2 = r.nextInt(80)+120;//下方柱子的y
			this.y2_h = screen_height - y2;//下方柱子高度
			this.y1_h = screen_height - y2_h - 75;
			this.y1 = y2  - height - 75; //上方柱头 y
			//x = 260;
		}
		
	}
//	Image src, 图片
//	int x_src, 从这个图片的x坐标开始截取
//	int y_src, 
//	int width, 截取宽度
//	int height, 
//	int transform, 旋转方式（0：不旋转，1...   一直到7，你可以自己试下）
//	int x_dest, 画到屏幕的位置的x坐标
//	int y_dest, 
//	int anchor Graphics.LEFT|Graphics.TOP
	public void paint(Graphics g)
	{
	//	System.out.println("Y2_H:"+y2_h);
		g.drawRegion(img2, 0, 0, width, y2_h, 0, x, y2, 20);
		g.drawRegion(img1, 0, height - y1_h, width, y1_h, 0, x, 0, 20);
		
	}

}
