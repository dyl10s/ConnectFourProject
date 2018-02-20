package Project2;

import javax.swing.JFrame;

public class ConnectFour {

	public static void main(String[] args) {
		
		int gameMode = 0;
		
		if (gameMode == 0){
			new ConnectFourPannel3D();
		}else{
			new ConnectFourPannel();
		}
		
		
	}
	
}
