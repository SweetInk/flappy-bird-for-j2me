package sweetink;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Num {
	/*Image ima ����ͼƬ��ͼƬ��������0~9����ȿ����У�
	 *int num ��Ϸ��Ҫ���ֳ���������
	 *int x,y ��������Ļ�ϵ�λ��
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
