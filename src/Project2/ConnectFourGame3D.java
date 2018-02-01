package Project2;

public class ConnectFourGame3D {

	private int[][][]	board;
	private int size;
	private int player;	
	public static final int USER =	0;
	public static final int COMPUTER =	1; 
	
	public ConnectFourGame3D(int size){
		
		this.size = size;
		
		board = new int[size][size][size];
		
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				for (int z = 0; z < size; z++){
					
					board[x][y][z] = -1;
					
				}	
			}	
		}
		
	}
	
	public int[][][] getBoard(){
		return board;
	}
	
	public int selectCol(int pX, int pY) {
		
		for (int i = size - 1; i >= 0; i--) {
			if (board[pX][pY][i] == -1) {
				board[pX][pY][i] = player;
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean canPlay(int x, int y, int z) {
		
		boolean canplay = false;
		
		for (int i = size - 1; i >= 0; i--) {
			if (board[x][y][i] == -1) {
				if (z == i) {
					return true;
				}else {
					break;
				}
			}
		}
		
		return canplay;
		
	}
	
	
	public void reset() {
		
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				for (int z = 0; z < size; z++){
					
					board[x][y][z] = -1;
					
				}	
			}	
		}
		
	}
	
	public int switchPlayer() {
		
		if (player == USER) {
			player = COMPUTER;
		}else {
			player = USER;
		}
		
		return player;
	}
	
	public int getPlayer() {
		return player;
	}

}
