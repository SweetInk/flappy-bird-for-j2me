package sweetink;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

import util.LPAudioPlayer;


public class FlappyBird extends GameCanvas implements Runnable ,CommandListener {
	public static final int gameMain = 0;
	public static final int gameReady = 1;
	public static final int gameIng  =2;
	public static final int gameOver = 3;
	BackGround bg; //背景类
	int gameState;
	int max_score;
	int temp_score;
	Num n;
	long TIME = 100;
	int timer = 0;//小鸟变换
	Gameover go;
	GameMain gm;
	int bg_timer=0;//背景变换
	Command pause; //暂停按钮
	boolean flag  =false;
	Pillar pi1,pi2,pi3;//新版
	Ground ground; //地面
	Bird bird; //鸟
	boolean gameover =false;
	int score = 0; //分数
	Image n_score = Image.createImage("/img/score/new.png");
	LPAudioPlayer playerSwing,playerHit,playerGetPoint,playerPause; //音频类
	public FlappyBird(boolean suppressKeyEvents) throws Exception {
	
		
		super(suppressKeyEvents);
		this.setFullScreenMode(true);
		
		n = new Num();
		max_score=score;
		gm = new GameMain(this);
		gameState=gameMain;
		pause = new Command("pasue",Command.OK,1);
		go = new Gameover(this);
		bg = new BackGround(this);
		ground = new Ground(this); //实例化
		pi1 =new Pillar(this.getWidth()+0,this);
		pi2 = new Pillar(this.getWidth()+pi1.width*3,this);
	//	pi3 = new Pillar(240+150+110, this);
		bird = new Bird(pi1.width,this);
		playerSwing = new LPAudioPlayer("audio/swing.wav", "audio/x-wav",true);
		playerGetPoint = new LPAudioPlayer("/audio/sfx_point.wav", "audio/x-wav",true);
		playerHit = new LPAudioPlayer("/audio/hit.wav", "audio/x-wav",true);
		playerPause = new LPAudioPlayer("audio/pause.wav", "audio/x-wav",true);
		
		this.addCommand(pause);
		this.setCommandListener(this);
		// TODO Auto-generated constructor stub
		new Thread(this).start();
		System.out.println("线程开始...");
	}
	//线程
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			long start = System.currentTimeMillis();
			//paint();
			input();
			logic();
			
			//游戏第一屏
			if(gameState==gameMain){
				System.out.println("游戏主界面...");
				paintGameMain();
			}
			//游戏第二屏
			else if(gameState==gameReady){
				System.out.println("游戏准备...");
				paintGameReady();
			}
			//游戏内容绘制
			else if(gameState==gameIng){
				System.out.println("游戏中...");
//					if(!gameover){
//					bg_timer++;
//					if(bg_timer%45==0){
//						bg.state=2;
//						}
//					timer++;
				paintGameIng();
				//repaint();	
				
			 }
			else if(gameState == gameOver){
				
				System.out.println("游戏结束");
				paintGameOver();
				
			}
			//serviceRepaints();
			long end = System.currentTimeMillis();
			if(end - start<TIME){
			//repaint();
			try {
				synchronized (this) {
					Thread.sleep(TIME-(end-start));
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}} 
		}
	}
	//游戏初始化绘图 1
	public void paintGameMain(){
		Graphics g = this.getGraphics();
		
		bg.paint(g);
		gm.paintMain(g);
		ground.paint(g);
		g.setColor(255, 0, 0);
		int x = this.getHeight()/5;
		for(int i = 0 ; i <this.getHeight();i+=x){
			g.drawLine(0, i , this.getWidth(), i);
		}
		flushGraphics();
	}
	//游戏初始化绘图 2
	public void paintGameReady(){
		Graphics g = this.getGraphics();
		
		bg.paint(g);
		gm.paintGameready(g);
		ground.paint(g);
		n.drawNumber(g, n.img_b, score, this.getWidth()/2, 2);
		flushGraphics();
	}
	//游戏中绘图
	public void paintGameIng(){
		Graphics g = this.getGraphics();
		bg.paint(g);
		bird.paint(g);
		pi1.paint(g);
		pi2.paint(g);
		//pi3.paint(g);
		ground.paint(g);
		n.drawNumber(g, n.img_b, score, this.getWidth()/2, 2);
		g.setClip(0,0,this.getWidth(),this.getHeight());
		g.setColor(255,0,0);
		int x = this.getHeight()/5;
		for(int i = 0 ; i <this.getHeight();i+=x){
			g.drawLine(0, i , this.getWidth(), i);
		}
		flushGraphics();
	}
	//游戏结束绘图
	public void paintGameOver(){
		Graphics g = this.getGraphics();
		
		bg.paint(g);
		ground.paint(g);
		go.paint(g);
		n.drawNumber(g, n.img_s, score, this.getWidth()/2+n.img_s.getWidth()/2, this.getHeight()*4/5-go.img.getHeight()/2-15);
		if(score>=max_score){max_score=score;}
		temp_score=score+1;
			n.drawNumber(g, n.img_s, max_score, this.getWidth()/2+n.img_s.getWidth()/2, this.getHeight()*4/5-go.img.getHeight()/2+15);
			g.setClip(0,0,this.getWidth(),this.getHeight());
			if(temp_score>max_score&&temp_score-1!=0)
			g.drawImage(n_score, this.getWidth()/2+n_score.getWidth()/2, this.getHeight()*4/5-go.img.getHeight()/2-30, 20);
			int x = this.getHeight()/5;
			g.setColor(255, 0, 150);
			for(int i = 0 ; i <this.getHeight();i+=x){
				g.drawLine(0, i , this.getWidth(), i);
			}
		flushGraphics();
	}
	
	//游戏重新初始
	public void init() throws Exception{
		timer=0;
		TIME = 100;
		pi1 =new Pillar(this.getWidth()+0,this);
		pi2 = new Pillar(this.getWidth()+pi1.width*3,this);
		//pi3 = new Pillar(240+150+110, this);
		bird = new Bird(pi1.width,this);
		score = 0;
	}
	//游戏输入事件
	public void input()
	{
		int k = getKeyStates();
		if(((k&GameCanvas.UP_PRESSED))!=0){
			playerSwing.play();
			bird.fly();
			timer=0;
			if(bird.state>1)
			bird.state--;
			}	
		else if((k&GameCanvas.FIRE_PRESSED)!=0){
			switch(gameState){
			case gameMain:
				playerPause.play();
				TIME = 100;
				gameState = gameReady;
				break;
			case gameReady:
				playerPause.play();
				TIME = 16;
				gameState = gameIng;
				break;
			case  gameIng:
				TIME = 16;
				break;
			case gameOver:
				playerPause.play();
				TIME = 100;
				gameState = gameReady;
				try {
					init();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			}
			System.out.println("GameState:"+gameState);
		}
		if(k==0&&timer>=25){bird.state=2;}
		if(k==0&&timer>=38){bird.state=3;}
	}
	public void paint(){
		this.getGraphics();
		switch(gameState){
		case gameMain:
		//	playerPause.play();
			paintGameMain();
			break;
		case gameReady:
			//playerPause.play();
			paintGameReady();
			break;
		case gameIng:
			paintGameIng();
			break;
		case gameOver:
		//	playerPause.play();
			paintGameOver();
			break;
		}
	}
	//
	protected void pointerPressed(int x, int y){
		switch(gameState){
		case gameMain:
			TIME = 100;
			playerPause.play();
			gameState = gameReady;
			break;
		case gameReady:
			playerPause.play();
			TIME = 16;
			gameState = gameIng;
			break;
		case  gameIng:{
			playerSwing.play();
			TIME = 16;
			bird.state=1;
			bird.fly();
			timer=0;
			if(bird.state>1)
				bird.state--;
		}
			break;
		case gameOver:
			playerPause.play();
			TIME = 100;
			gameState = gameReady;
			try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		}
	}
	//游戏逻辑处理
public void logic(){
	switch(gameState){
	case gameMain:
		gm.step();
		ground.step();
		break;
	case gameReady:
		gm.step2();
		ground.step();
		break;
	case gameIng:
		if(!gameover){
		bg_timer++;
		if(bg_timer%45==0){
			//bg.state=2;
			}
		timer++;
		}
		bird.step();
		pi1.step(pi2);
		pi2.step(pi1);

		ground.step();
		if(bird.pass(pi1, pi2)){
			score++;
			playerGetPoint.play();

		}
	if(bird.hitWithGround(ground)){
		
		gameState = gameOver;
			bird.state=4;	
			playerHit.play();
	}
break;
		
	case gameOver:break;
	}
}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if(c==pause){
			if(!gameover){
				gameover = true;
				//playerPause.play();
			}
			else if(gameover){
				gameover = false;
			//	playerPause.play();
			}
		}
		}
	}


