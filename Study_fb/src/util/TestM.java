package util;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class TestM extends MIDlet {
	Display d;
	Form f;
	public TestM() {
		// TODO Auto-generated constructor stub
		f = new Form("Test");
		d = Display.getDisplay(this);
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
	}

}
