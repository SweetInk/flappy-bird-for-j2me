package util;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LPAudioPlayer player = new LPAudioPlayer("swing.wav", "audio/x-wav");
		player.loadResource();
		player.play();
		player.replay();
	}

}
