package sweetink;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class Main extends MIDlet {
	Display d = null;
	FlappyBird fb = null;
	Img img = null;
	//TestCanvas tc;
	public Main() throws Exception {
		// TODO Auto-generated constructor stub
		d = Display.getDisplay(this);
		fb = new FlappyBird(true);
		//img = new Img();
	//	tc = new TestCanvas(true);
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		d.setCurrent(fb);
		
	}

}
