package sweetink;

import java.util.Random;

public class Rd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int k = r.nextInt();
		int j = Math.abs(k%60)+30;
		System.out.println("x:"+j);
	}

}
