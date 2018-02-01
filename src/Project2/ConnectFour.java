package Project2;

import javax.swing.JFrame;

public class ConnectFour {

	public static void main(String[] args) {
		
		int gameMode = 0;
		
		if (gameMode == 0){
			JFrame game3d = new ConnectFourPannel3D();
		}else{
			JFrame game = new ConnectFourPannel();
		}
		
		
	}
	
}
