package util;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class TestMain extends MIDlet {
	Display d;
	Form f;
	LPAudioPlayer player ;
	public TestMain() {
		// TODO Auto-generated constructor stub
		player = new LPAudioPlayer("swing.wav", "audio/x-wav");
		player.loadResource();
		d = Display.getDisplay(this);
		f = new Form("“Ù∆µ—› æ");
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
		d.setCurrent(f);
		//player.play();
		player.replay();
	}

}
