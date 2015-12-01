package sweetink;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Bird {
	int px;
	Image []img;
	Image []img2;
	Image []img3;
	Image img4;
	public int state;
	/** �����λ��, ���λ����������� */
	int x;	int y;
	/** ���нǶ� */
	double angle;
	int width;
	int height;
	/** ��ǰͼƬ��� */
	int index = 0;
	/** �������ٶ� */
	final double g; 
	/** ʱ������ */
	final double t;
	/** ��ʼ�����ٶ� */
	final double v0;
	/** ��ǰ�����ٶ� */
	double speed;
	/** �ƶ����� */
	double s;
	int size = 24;
	
	public Bird(int x1,Canvas c) throws Exception {
		this.state=1; //��ǰ״̬   1.С������  2.С������  3.С����ֱ����
		this.g = 4; //�������ٶ�
		this.t = 0.25; //ÿ�μ���ļ��ʱ��
		this.v0 = 15; //��ʼ�����ٶ�
		x = c.getWidth()/3; //��ĳ�ʼλ��
		y = c.getHeight()/5; //��ĳ�ʼλ��
		//���ض���֡
		System.out.println("@X=:"+x1);
		px = x1;
		img = new Image[3];
		img2= new Image[3];
		img3= new Image[3];
		img4 = Image.createImage("/img/bird/bird33.png");
		float rate = 0.95f;
		int nx =(int)(px*rate);
		int ny = (nx*24/34);
		System.out.println("@new x:"+nx);
		System.out.println("@new y:"+ny);
	
		for(int i = 0; i < 3; i++)
		{
			img [i]= Image.createImage("/img/bird/bird"+(i+11+".png"));//С������
			
			img2 [i]= Image.createImage("/img/bird/bird"+(i+21+".png"));//С������
			
			img3 [i]= Image.createImage("/img/bird/bird"+(i+31+".png"));//С����ֱ����
		
		}
		for(int i = 0; i< 3;i++){
		//	img[i]=ImageUtil.scale(img[i], nx, ny);
		//	img2[i]=ImageUtil.scale(img2[i], nx, ny);
		//	img3[i]=ImageUtil.scale(img3[i], nx, ny);
			//System.out.println("img"+i+",width:"+img[i].getWidth());
		}
		width = img[0].getWidth();
		height = img[0].getHeight();
		System.out.println("С���:"+width);
		System.out.println("С���:"+height);
	}
	
	/** ����һ��  
	 * ��ֱ����λ�Ƽ���
	 *  (1) �����ٶȼ��� V=Vo-gt  
		(2) ���׾������ S=Vot-1/2gt^2
	 * */
	public void step(){
		//V0 �Ǳ���
		double v0 = speed;
		//���㴹ֱ�����˶�, ����ʱ��t�Ժ���ٶ�, 
		double v = v0 - g*t;
		//��Ϊ�´μ���ĳ�ʼ�ٶ�
		speed = v;
		//���㴹ֱ�����˶������о���
		s = v0*t - 0.5 * g * t * t;
		//������ľ��� ����Ϊ y����ı仯
		y = y - (int)s;
		
		if(state==4){
			
			if(y>250)y=250;
		}
		//����С��Ķ���֡ͼƬ, ����/4 ��Ϊ�˵����������µ�Ƶ��
		index++;
		if(index>=3)
			index=0;
	;
	}
	/** С�����Ϸ� */
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
	/** �ж����Ƿ���ط�����ײ */
	public boolean hitWithGround(Ground ground){
		if((this.y+20>=ground.y)){
		return true;}
		else{ return false;}
		
	}
	/*
	 * �ж����Ƿ���������
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
	public boolean pass(Pillar col1, Pillar col2) {
		return (col1.x+42==x || col2.x+42==x);
	}
	
	/** ��鵱ǰ���Ƿ��������� */
	
}
