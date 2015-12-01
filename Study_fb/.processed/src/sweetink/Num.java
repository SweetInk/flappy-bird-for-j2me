package sweetink;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Num {
	/*Image ima 数字图片（图片中数字由0~9横向等宽排列）
	 *int num 游戏中要体现出来的数字
	 *int x,y 数字在屏幕上的位置
	 */
	  String buff;
	  int w;
	 
	 public Image img_s;
	 public Image img_b; 
	  int ch;
	  public Num() throws IOException {
		
		 img_s = Image.createImage("/img/score/num_s.png");
		 img_b = Image.createImage("/img/score/num_b.png");
	  }
	  void drawNumber(Graphics g,Image img,int num,int x,int y)
	  { 
		  w = img.getWidth() / 10;
	    buff = Integer.toString(num);
	   
	    for (int i=0; i<buff.length(); i++)
	    {
	      g.setClip(x,y,w,img.getHeight());
	      ch = buff.charAt(i) - '0';
	      g.drawImage(img,x-ch*w,y,0);
	      x += w;
	    }
	   
	  }
	 

}
