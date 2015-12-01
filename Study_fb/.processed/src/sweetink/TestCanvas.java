package sweetink;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;


public class TestCanvas extends GameCanvas implements Runnable {
	Image bg;

	Num n;
	int bx,by;
	Bird bird;
	GameMain gm;
	Image i = Image.createImage("/img/score/num_b.png");
	int px,py;
	/*Image ima 数字图片（图片中数字由0~9横向等宽排列）
	 *int num 游戏中要体现出来的数字
	 *int x,y 数字在屏幕上的位置
	 */
	  String buff;
	  int w;
	  int ch;
	  void drawNumber(Graphics g,Image ima,int num,int x,int y)
	  {
	    buff = Integer.toString(num);
	    w = ima.getWidth() /10;
	    for (int i=0; i<buff.length(); i++)
	    {
	      g.setClip(x,y,w,ima.getHeight());
	      ch = buff.charAt(i) - '0';
	      g.drawImage(ima,x-ch*w,y,0);
	      x += w;
	    }
	  }
	 
	protected TestCanvas(boolean suppressKeyEvents) throws Exception {
		super(suppressKeyEvents);
		n = new Num();
		bird = new Bird();
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		bg = Image.createImage("/img/item/bg_sun.png");
		bx = 100;
		by=100;
		px  = 50;
		gm = new GameMain(this);
		py=50;
		new Thread(this).start();
	}
public void paint(){
	Graphics g = this.getGraphics();
	
	g.drawImage(bg, 0, 0, 20);
	gm.paintGameready(g);
	//drawNumber(g, i, 15, 15, 15);
	//n.drawNumber(g, i,15, 15, 15);
	bird.state=4;
	bird.paint(g);
//	g.drawImage(pillar, 150, 150, 20);
//	g.drawImage(bird, 50, 50, 20);
	flushGraphics();
	
}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			paint();
			gm.step2();
			bird.step();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
