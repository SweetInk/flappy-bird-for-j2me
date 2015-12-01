package sweetink;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import util.ImageUtil;

public class Img extends Canvas implements Runnable{
	public Image img=null;
	int x,y;
	public Img(){
		try {
			img = Image.createImage("/img/item/bg_sun.png");
			img =  ImageUtil.scale2(img, getWidth(), getHeight());
			
			x = 0;y=0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(this).start();
	}
	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, 0, 0, 20);
		g.setColor(0);
		g.drawRect(x, y, 10, 10);
		
	}
	protected void keyPressed(int key){
		System.out.println("KeyPressed");
		int k = getGameAction(key);
		if(k==Canvas.UP)
			y-=1;
		else if(k==Canvas.DOWN)
			y+=1;
		else if(k==Canvas.LEFT)
			x-=1;
		else if (k==Canvas.RIGHT)
			x+=1;
		System.out.println("x:"+x);
		//repaint();
	}
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
}
