package util;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class LPAudioPlayer {
	private Player player;
	private String filename;
	private String format;
	/*
	 * Construct method
	 */
	public LPAudioPlayer(String filename,String format,boolean isLoad){
		this.format = format;
		this.filename = filename;
		if(isLoad){
			loadResource();
		}
	}
	public LPAudioPlayer(String filename,String format){
		this.format = format;
		this.filename = filename;
	}
	/*
	 * load resource
	 */
	public void loadResource() {
		// TODO Auto-generated method stub
		InputStream is= getClass().getResourceAsStream("/"+filename);
		try {
			player = Manager.createPlayer(is,format);
			player.realize();
		//	player.prefetch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * set loop
	 */
	public void setLoop(){
		if(player!=null){
			player.setLoopCount(-1);
		}
	}
	/*
	 * set volume
	 */
	public void setVolume(int level){
		if(player!=null){
			VolumeControl control = (VolumeControl) player.getControl("VolumeControl");
			control.setLevel(level);
		}
	}
	/*
	 * stop to play the mid file
	 */
	public void stop(){
		if(player!=null){
			try {
				player.stop();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * play the music file
	 */
	public void play(){
		if(player!=null){
			try {
				
				player.start();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * replay the music file
	 */
	public void replay(){
		close(); //close sound interface
		System.gc(); //
		loadResource(); // reload resource
		play(); //continue play file
	}
	public void close(){
		if(player!=null){
			player.close();
			player = null;
		} 
	}
}
