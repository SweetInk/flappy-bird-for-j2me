package sweetink;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Gameover {
	Image img;
	int x;
	int y;
	Canvas c;
	public Gameover(Canvas c) throws IOException{
		img = Image.createImage("/img/ui/gameover.png");
		this.c  = c;
	}
	public void paint(Graphics g){
		g.drawImage(img, (c.getWidth()-img.getWidth())/2, c.getHeight()*4/5-img.getHeight(), 20);
	}
}
