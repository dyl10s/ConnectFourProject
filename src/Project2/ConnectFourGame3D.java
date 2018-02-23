package Project2;

public class ConnectFourGame3D {

	private ConnectFourGame[] board;
	private int size;
	private int player;	
	public static final int USER =	0;
	public static final int COMPUTER =	1; 
	
	public ConnectFourGame3D(int size){
	
		this.size = size;
		
		board = new ConnectFourGame[size];
		
		for (int b = 0; b < size; b++){
			board[b] = new ConnectFourGame(size);
		}
		
		System.out.println(this.toString());
		
	}
	
	public ConnectFourGame[] getBoard(){
		return board;
	}
	
	public int selectCol(int pX, int pY) {
		
		return board[pX].selectCol(pY);
		
	}
	
	public boolean isWinner(int playerNum){
		
		//Check all 2d directions
		for (int x = 0; x < size; x++){
			if (board[x].isWinner(playerNum) == true){
				return true;
			}
		}
		
		
		if (checkDirection(0, 0, 1, playerNum) >= 4){
			return true;
		}
		
		if (checkDirection(0, 1, 1, playerNum) >= 4){
			return true;
		}
		
		if (checkDirection(1, 0, 1, playerNum) >= 4){
			return true;
		}
		
		if (checkDirection(-1, 0, 1, playerNum) >= 4){
			return true;
		}
		
		if (checkDirection(0, -1, 1, playerNum) >= 4){
			return true;
		}
		
		return false;
	}
	
	public int checkDirection(int xChange, int yChange, int zChange, int playerNum){
		
		int highscore = 0;
		
		int inRow = 0;
		boolean hasNext = true;
		
		int currentX = xChange;
		int currentY = yChange;
		int currentZ = zChange;
		
		int player = playerNum;
		
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				for (int z = 0; z < size; z++){
					
					if (this.getBoard()[z].getBoard()[x][y] == player){
						
						inRow += 1;
						
					}
					
					do{
						
						if(z + currentZ >= size  || y + currentY >= size || x + currentX >= size){
							currentX = xChange;
							currentY = yChange; 
							currentZ = zChange;
							break;
						}
						
						if(z + currentZ < 0  || y + currentY < 0 || x + currentX < 0){
							currentX = xChange;
							currentY = yChange; 
							currentZ = zChange;
							break;
						}
						
						int currentSpace = this.getBoard()[z + currentZ].getBoard()[x + currentX][y + currentY]; 
										
						if (currentSpace == player){
							inRow += 1;
							hasNext = true;
							currentX += xChange;
							currentY += yChange; 
							currentZ += zChange; 
						}else{
							hasNext = false;
							currentX = xChange;
							currentY = yChange; 
							currentZ = zChange;
						}
						
					}while(hasNext);
					
					if (inRow > highscore){
						highscore = inRow;
					}
					
					inRow = 0;
					
				}
			}
		}
		
		return highscore;
	}
	
	public boolean canPlay(int x, int y, int z) {
		
		return board[x].canPlay(y, z);
		
	}
	
	public String toString(){
		
		String returnS = "";
		
		for (ConnectFourGame b: board){
			returnS += "-,";
		}
		
		return returnS;
		
	}
	
	public void reset() {
		
		for (ConnectFourGame b: board){
			b.reset();
		}
		
	}
	
	public int switchPlayer() {
		
		for (int x = 0; x < size; x++){
			board[x].switchPlayer();
		}
		
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
