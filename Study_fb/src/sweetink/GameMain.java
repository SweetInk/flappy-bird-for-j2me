package sweetink;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;

public class GameMain {
	Image img,img2;
	int x,y;
	int x1,y1;
	int index;
	Image bird[];
	Canvas c;
	public GameMain(Canvas c) throws IOException{
		index = 0;
		this.c = c;
		bird = new Image[3];
		img = Image.createImage("/img/ui/gamemain.png");
		img = ImageUtil.scale(img, c.getWidth()/2, c.getHeight()/2);
		img2 = Image.createImage("/img/ui/gameready.png");
		for(int i = 0; i < 3; i++){
			bird[i] = Image.createImage("/img/bird/bird"+(i+1)+".png");
		}
		x1 = c.getWidth()/2-bird[0].getWidth()*2;
		y1 = c.getHeight()/2-bird[0].getHeight()*2;
		x = c.getWidth()/2 - bird[0].getWidth()/2;
		y = c.getHeight() /2 - bird[0].getHeight()/2;
	}
	public void  step(){
		y++;
		if(y>100)y=95;
		index++;
		if(index>=3)
			index=0;
	}
	public void step2(){
		y1++;
		if(y1>140)y1=135;
		index++;
		if(index>=3)
			index=0;
		
	}
	public void paintMain(Graphics g){
	//	System.out.println("ÆÁÄ»¿í:"+c.getWidth());
	//	System.out.println("ÆÁÄ»¸ß:"+c.getHeight());
	//	System.out.println("Í¼Æ¬¸ß:"+img.getHeight());
	//	System.out.println("Í¼Æ¬¿í:"+img.getWidth());
		//g.drawString("Center", c.getWidth()/2-"Center".length()/2, c.getHeight()/2, 0);
		g.drawImage(img, c.getWidth()/2-img.getWidth()/2, c.getHeight()*4/5-img.getHeight(), 0);
		g.drawImage(bird[index], x, y, 20);
	}
	public void paintGameready(Graphics g){
		g.drawImage(img2, (c.getWidth()-img2.getWidth())/2, (c.getHeight()-img2.getHeight())/2, 20);
		g.drawImage(bird[index], x1, y1, 20);
	}

}
