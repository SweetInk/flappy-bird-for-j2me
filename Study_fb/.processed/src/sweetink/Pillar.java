package sweetink;
import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;


public class Pillar {
	int x, y1 = 0,y2;//���ӵ�x ��y
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
		System.out.println("��Ļ��:"+c.getHeight()/2);
		float y = img1.getHeight();
		float rate =( c.getHeight())/y; //����
		System.out.println("Rate:"+rate);
		System.out.println("new x:"+img1.getWidth()*rate);
		img1 = ImageUtil.scale(img1, img1.getWidth(), c.getHeight()); //����
		System.out.println("IMG_W:"+img1.getWidth()+",H:"+img1.getHeight());
		img2 = ImageUtil.scale(img2, img2.getWidth(), c.getHeight());
		width = img1.getWidth();
		this.screen_height = c.getHeight();
		this.screen_width = c.getWidth();
		height = img2.getHeight();
		this.x = x;//����x������ͬ
		this.y2 = r.nextInt(80)+120;//�·����ӵ�y
		this.y2_h = screen_height - y2;//�·����Ӹ߶�
		this.y1_h = screen_height - y2_h - 75; //�Ϸ����Ӹ߶�
		this.y1 = y2  - height - 75; //�Ϸ���ͷ y
	}
	
	
	//ʵϰ�����ƶ�
	public void step(){
		x-=1;
		//System.out.println("��ǰx:"+x);
		if(x<=-40){
			
			x = screen_width;
			
			this.y2 = r.nextInt(80)+120;//�·����ӵ�y
			this.y2_h = screen_height - y2;//�·����Ӹ߶�
			this.y1_h = screen_height - y2_h - 75;
			this.y1 = y2  - height - 75; //�Ϸ���ͷ y
			//x = 260;
		}
		
	}
//	Image src, ͼƬ
//	int x_src, �����ͼƬ��x���꿪ʼ��ȡ
//	int y_src, 
//	int width, ��ȡ���
//	int height, 
//	int transform, ��ת��ʽ��0������ת��1...   һֱ��7��������Լ����£�
//	int x_dest, ������Ļ��λ�õ�x����
//	int y_dest, 
//	int anchor Graphics.LEFT|Graphics.TOP
	public void paint(Graphics g)
	{
	//	System.out.println("Y2_H:"+y2_h);
		g.drawRegion(img2, 0, 0, width, y2_h, 0, x, y2, 20);
		g.drawRegion(img1, 0, height - y1_h, width, y1_h, 0, x, 0, 20);
		
	}

}
