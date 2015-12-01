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
//		Image src, ͼƬ
//		int x_src, �����ͼƬ��x���꿪ʼ��ȡ
//		int y_src, 
//		int width, ��ȡ���
//		int height, 
//		int transform, ��ת��ʽ��0������ת��1...   һֱ��7��������Լ����£�
//		int x_dest, ������Ļ��λ�õ�x����
//		int y_dest, 
//		int anchor Graphics.LEFT|Graphics.TOP
		
		g.drawRegion(img, 0, 0, width, c.getHeight()/5, 0, x, y, 20);
		//g.drawImage(img, x, y,0);
	}
}
