package sweetink;
import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;


public class BackGround {
	Image img,img2;
	int state;
	int x;
	int y;
	public BackGround(Canvas c) throws IOException{
		state=1;
		img = Image.createImage("/img/item/bg_sun.png");
		img = ImageUtil.scale(img, c.getWidth(), c.getHeight());
		img2 = Image.createImage("/img/item/bg_night.png");
	//	System.out.println("ScreenWidth:"+c.getWidth());
		x = 0;
		y = 0;
	}
	public void paint(Graphics g){
		if(state==1){
		g.drawImage(img, x, y, 20);
		//System.out.println("Image Width:"+img.getWidth());
		}
		else if(state==2)g.drawImage(img2, 0, 0, 20);
	}
}
