package sweetink;
import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;


public class Ground {
	Image img;
	int x;
	int y;
	int width;
	int height;
	Canvas c;
	public Ground(Canvas c) throws IOException{
		img = Image.createImage("/img/item/ground.png");
		int y = img.getHeight();
		if(c.getHeight()/5>y)
			y = c.getHeight()/5;
			img = ImageUtil.scale(img, c.getWidth()/13+c.getWidth(), y);
		width = img.getWidth();
		height = img.getHeight();
		this.c = c;
		this.x = 0;
		this.y = c.getHeight() - c.getHeight()/5;
		}
	public void step(){
		x-=1;
		if(x<=-(c.getWidth()/13))
			x=0;
	}
	public void paint(Graphics g){
		//System.out.println("-----------------");
//		Image src, 图片
//		int x_src, 从这个图片的x坐标开始截取
//		int y_src, 
//		int width, 截取宽度
//		int height, 
//		int transform, 旋转方式（0：不旋转，1...   一直到7，你可以自己试下）
//		int x_dest, 画到屏幕的位置的x坐标
//		int y_dest, 
//		int anchor Graphics.LEFT|Graphics.TOP
		
		g.drawRegion(img, 0, 0, width, c.getHeight()/5, 0, x, y, 20);
		//g.drawImage(img, x, y,0);
	}
}
