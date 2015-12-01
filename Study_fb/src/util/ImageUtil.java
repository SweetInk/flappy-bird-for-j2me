package util;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ImageUtil {
	public static Image scale(Image img,int newW, int newH){
		int srcW = img.getWidth();
		int srcH = img.getHeight();
		Image tmp = Image.createImage(newW, srcH);
		Graphics g = tmp.getGraphics();
		System.out.println("Staring convert...");
		for(int x = 0; x <newW;x++){
			g.setClip(x, 0, 1, srcH);
			g.drawImage(img, x-x*srcW/newW, 0, Graphics.LEFT|Graphics.TOP);
		}
		Image dst  = Image.createImage(newW, newH);
		g = dst.getGraphics();
		for(int y = 0; y< newH;y++){
			g.setClip(0, y, newW, 1);
			g.drawImage(tmp, 0, y-y*srcH/newH, Graphics.LEFT|Graphics.TOP);
		}
		System.out.println("All pixe has compeleted...");
		System.out.println("dst-x:"+dst.getWidth());
		System.out.println("dst-y:"+dst.getHeight());
		return dst;
	}
	public static Image scale2(Image img,int newW,int newH){
		int srcW = img.getWidth();
		int srcH = img.getHeight();
		Image dst =Image.createImage(newW, newH);
		Graphics g = dst.getGraphics();
		System.out.println("开始计算...");
		for(int x = 0; x<newW;x++){
			for(int y = 0; y < newH;y++){
				g.setClip(x, y, 1, 1);
				g.drawImage(img, x-x*srcW/newW, y-y*srcH/newH, Graphics.LEFT|Graphics.TOP);
			}
		}
		System.out.println("计算完成");
		return dst;
	}

}
