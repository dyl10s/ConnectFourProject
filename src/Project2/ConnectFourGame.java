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
	
	public int[][] getBoard(){
		return board;
	}
	
	public int selectCol(int pCol) {
		
		for (int i = size - 1; i >= 0; i--) {
			if (board[pCol][i] == -1) {
				board[pCol][i] = player;
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean canPlay(int x, int y) {
		
		boolean canplay = false;
		
		for (int i = size - 1; i >= 0; i--) {
			if (board[x][i] == -1) {
				if (y == i) {
					return true;
				}else {
					break;
				}
			}
		}
		
		return canplay;
		
	}
	
	public String toString() {
		
		String s = "";
		
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {	
				s += Integer.toString(board[x][y]);
			}
			s += "\n";
		}
		
		return s;
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
	
	public int getPlayer() {
		return player;
	}
		
	
	public int runAI() {
		
		int[][] boardScore = getBoardScore();
		int highX = 0;
		int highY = 0;
		int highscore = 0;
		
		for (int x = 0; x < size ; x++) {
			for (int y = 0; y < size; y++) {
				
				if (canPlay(x, y)) {
					
					if (Math.abs(boardScore[x][y]) > Math.abs(highscore)) {
						
						highscore = boardScore[x][y];
						highX = x;
						highY = y;
					
					}
					
				}
				
			}
				
		}
		
		System.out.println(highscore);
		System.out.println(highX);
		System.out.println(highY);
		
		selectCol(highX);
		return highX;
		
	}
	
	public int[][] getBoardScore() {
		
		int[][] boardScore = new int[size][size];
		
		for (int x = 0; x < size ; x++) {
			for (int y = 0; y < size; y++) {
				
				if (board[x][y] == -1) {
					
					//if there is no player then the score is 1
					boardScore[x][y] += 1;
					
				}else {
					
					//Player chip the the chip we are looking at.
					int playerChip = -1;
					
					
					//This assigns a player value to the chip for future reference.
					if (board[x][y] == USER) {			
						playerChip = USER;
					}else {
						playerChip = COMPUTER;
					}
					
					//this is how many chips away we are searching
					int distance = 1;
					
					//the next empty chip will be 5 points (+ or - based on player)
					int nextScore = 5;
					int inRowValue = 5;
					
					//Convert score to negative if it was a players chip
					if (playerChip == USER) {
						nextScore *= -1;
						inRowValue *= -1;
					}

					
					//search positive x values (Make sure we are not on the edge)
					if (x + 1 < size) {
						while(board[x + distance][y] == playerChip) {
							
							nextScore += inRowValue;
							
							if (x + (distance + 1) >= size) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (x + (distance) < size) {
						boardScore[x + distance][y] += nextScore;
					}
					
					
					//reset for next check
					nextScore = 0 + inRowValue;
					distance = 1;
					
					//search negative x values (Make sure we are not on the edge)
					if (x - 1 > -1) {
						while(board[x - distance][y] == playerChip) {
							
							nextScore += inRowValue;
							
							if (x - (distance + 1) <= -1) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (x - (distance) > -1) {
						boardScore[x - distance][y] += nextScore;
					}
					
					//reset for next check
					nextScore = 0 + inRowValue;
					distance = 1;
					
					//search positive y values (Make sure we are not on the edge)
					if (y + 1 < size) {
						while(board[x][y + distance] == playerChip) {
							
							nextScore += inRowValue;
							
							if (y + (distance + 1) >= size) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (y + (distance) < size) {
						boardScore[x][y + distance] += nextScore;
					}
								
					//reset for next check
					nextScore = 0 + inRowValue;
					distance = 1;
					
					//search negative y values (Make sure we are not on the edge)
					if (y - 1 > -1) {
						while(board[x][y - distance] == playerChip) {
							
							nextScore += inRowValue;
							
							if (y - (distance + 1) <= -1) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (y - (distance) > -1) {
						boardScore[x][y - distance] += nextScore;
					}
					
					//reset for next check
					nextScore = 0 + inRowValue;
					distance = 1;
					
					//search negative diagonal values (Make sure we are not on the edge)
					if (y - 1 > -1 && x - 1 > -1) {
						while(board[x - distance][y - distance] == playerChip) {
							
							nextScore += inRowValue;
							
							if (y - (distance + 1) <= -1 || x - (distance + 1) <= -1) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (y - (distance) > -1 && x - (distance) > -1) {
						boardScore[x - distance][y - distance] += nextScore;
					}
					
					//reset for next check
					nextScore = 0 + inRowValue;
					distance = 1;
					
					//search positive diagonal values (Make sure we are not on the edge)
					if (y - 1 > -1 && x + 1 < size) {
						while(board[x + distance][y - distance] == playerChip) {
							
							nextScore += inRowValue;
							
							if (y - (distance + 1) <= -1 || x + (distance + 1) >= size) {
								break;
							}else {
								distance += 1;
							}
							
						}
					}
					
					//Add nextScore to the open slot if its not out of bounds
					if (y - (distance) > -1 && x + (distance) < size) {
						boardScore[x + distance][y - distance] += nextScore;
					}
					
				}
				

				
			}
			
		}
		
		for (int x = 0; x < size; x++) {
			for(int y=0; y< size; y++) {
				
				if (board[x][y] == 0) {
					boardScore[x][y] = 0;
				}
				
				if (board[x][y] == 1) {
					boardScore[x][y] = 0;
				}
				
			}
		}
		
		return boardScore;
		
	}
	
	public boolean isWinner( int person ) {
		
		for (int x = 0; x < size; x++) {
			for(int y=0; y< size; y++) {
				if (board[x][y] == person) {
					
					int inRowVertical = 1;
					
					if (y + 3 < size) {
						while(board[x][y + inRowVertical] == person) {
							inRowVertical += 1;
							
							if (inRowVertical >= 4) {
								return true;
							}
							
						}
					}
					
					int inRowHorizontal = 1;
					
					if (x + 3 < size) {
						while(board[x + inRowHorizontal][y] == person) {
							inRowHorizontal += 1;
							
							if (inRowHorizontal >= 4) {
								return true;
							}
							
						}
						
					}
			
					int inRowDiag = 1;
					
					if (x + 3 < size && y + 3 < size) {
						while(board[x + inRowDiag][y + inRowDiag] == person) {
							inRowDiag += 1;
						
							if (inRowDiag >= 4) {
								return true;
							}
							
						}
						
					}
					
                    inRowDiag = 1;
					
					if (x - 3 > 0 && y + 3 < size) {
						while(board[x - inRowDiag][y + inRowDiag] == person) {
							inRowDiag += 1;
						
							if (inRowDiag >= 4) {
								return true;
							}
							
						}
						
					}
					
				}
			}
		}
		
		return false;
	}
	
}
