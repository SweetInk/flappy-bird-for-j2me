package sweetink;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Bird {
	Image []img;
	Image []img2;
	Image []img3;
	Image img4;
	public int state;
	/** 鸟飞行位置, 这个位置是鸟的坐标 */
	int x;	int y;
	/** 飞行角度 */
	double angle;
	int width;
	int height;
	/** 当前图片序号 */
	int index = 0;
	/** 重力加速度 */
	final double g; 
	/** 时间间隔秒 */
	final double t;
	/** 初始上抛速度 */
	final double v0;
	/** 当前上抛速度 */
	double speed;
	/** 移动距离 */
	double s;
	int size = 24;
	
	public Bird() throws Exception {
		this.state=1; //当前状态   1.小鸟向上  2.小鸟向下  3.小鸟竖直向下
		this.g = 4; //重力加速度
		this.t = 0.25; //每次计算的间隔时间
		this.v0 = 15; //初始上抛速度
		x = 50; //鸟的初始位置
		y = 100; //鸟的初始位置
		//加载动画帧
		img = new Image[3];
		img2= new Image[3];
		img3= new Image[3];
		img4 = Image.createImage("/img/bird/bird33.png");
		for(int i = 0; i < 3; i++)
		{
			img [i]= Image.createImage("/img/bird/bird"+(i+11+".png"));//小鸟向上
			img2 [i]= Image.createImage("/img/bird/bird"+(i+21+".png"));//小鸟向下
			img3 [i]= Image.createImage("/img/bird/bird"+(i+31+".png"));//小鸟竖直向下
		}
		width = img[0].getWidth();
		height = img[0].getHeight();
	}
	
	/** 飞行一步  
	 * 竖直上抛位移计算
	 *  (1) 上抛速度计算 V=Vo-gt  
		(2) 上抛距离计算 S=Vot-1/2gt^2
	 * */
	public void step(){
		//V0 是本次
		double v0 = speed;
		//计算垂直上抛运动, 经过时间t以后的速度, 
		double v = v0 - g*t;
		//作为下次计算的初始速度
		speed = v;
		//计算垂直上抛运动的运行距离
		s = v0*t - 0.5 * g * t * t;
		//将计算的距离 换算为 y坐标的变化
		y = y - (int)s;
		
		if(state==4){
			
			if(y>250)y=250;
		}
		//更换小鸟的动画帧图片, 其中/4 是为了调整动画更新的频率
		index++;
		if(index>=3)
			index=0;
	;
	}
	/** 小鸟向上飞 */
	public void fly()
	{
		speed = v0;
	}
	
	public void paint(Graphics g){
		
		if(state ==1)
			g.drawImage(img[index], x, y, 20);
			else if(state==2){
				g.drawImage(img2[index], x, y, 20);
			}
		if(state==3){
			g.drawImage(img3[index], x, y, 20);
		}
		if(state==4){
			g.drawImage(img4, x, y, 20);
		}
	}
	/** 判断鸟是否与地发生碰撞 */
	public boolean hitWithGround(Ground ground){
		if((this.y+20>=ground.y)){
		return true;}
		else{ return false;}
		
	}
	/*
	 * 判断鸟是否碰到柱子
	 */
	
	public boolean hitPillar(Pillar pi){
		if(hitPillarDown(pi)||hitPillarUp(pi))return true;
		else return false;
	}
	public boolean hitPillarDown(Pillar pi){
		if((x+width>=pi.x && x<=pi.x+pi.width)&&(y+20>=pi.y2 && y<=pi.y2+pi.y2_h))
		return true;
		else return false;
	}
	public boolean hitPillarUp(Pillar pi){
		if((x+width>=pi.x && x <= pi.x+pi.width)&&(y+20>=0 && y<=pi.y1_h))
		return true;
		else return false;
	}
	public boolean pass(Pillar col1, Pillar col2,Pillar col3) {
		return (col1.x+45==x || col2.x+45==x) || (col3.x+45==x);
	}
	
	/** 检查当前鸟是否碰到柱子 */
	
}
