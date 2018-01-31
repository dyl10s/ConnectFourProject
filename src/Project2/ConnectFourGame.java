package Project2;

public class ConnectFourGame {

	private int[][]	board;
	private int size;
	private int player;	
	public static final int USER =	0;
	public static final int COMPUTER =	1; 
	
	public ConnectFourGame(int pSize) {
		size = pSize;
		board = new int[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = -1;
			}
		}
		
		player = 0;
	}
	
	public int selectCol(int pCol) {
		
		for (int i = 0; i < size; i++) {
			if (board[pCol][i] == -1) {
				board[pCol][i] = player;
				return i;
			}
		}
		
		return -1;
	}
	
	public void reset() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = -1;
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
	
	public boolean isWinner( int person ) {
		
		for (int x = 0; x < size; x++) {
			for(int y=0; y< size; y++) {
				if (board[x][y] == person) {
					
					int inRowVertical = 1;
					
					if (y + 3 < size) {
						while(board[x][y + inRowVertical] == person) {
							inRowVertical += 1;
						}
						
						if (inRowVertical >= 4) {
							return true;
						}
					}
					
					int inRowHorizontal = 1;
					
					if (x + 3 < size) {
						while(board[x + inRowHorizontal][y] == person) {
							inRowHorizontal += 1;
						}
						
						if (inRowHorizontal >= 4) {
							return true;
						}
					}
			
					int inRowDiag = 1;
					
					if (x + 3 < size && y + 3 < size) {
						while(board[x + inRowDiag][y + inRowDiag] == person) {
							inRowDiag += 1;
						}
						
						if (inRowDiag >= 4) {
							return true;
						}
					}
					
                    inRowDiag = 1;
					
					if (x - 3 < size && y + 3 < size) {
						while(board[x - inRowDiag][y + inRowDiag] == person) {
							inRowDiag += 1;
						}
						
						if (inRowDiag >= 4) {
							return true;
						}
					}
					
				}
			}
		}
		
		return false;
	}
	
}
