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
		
		return false;
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
