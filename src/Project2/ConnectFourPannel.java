package Project2;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConnectFourPannel extends JFrame implements ActionListener {

	private JLabel[][] board;
	/** GUI visual for the game board model */
	private JButton[] selection;
	/** buttons to select game columns */
	private ConnectFourGame game;
	/** reference to the game model */
	private int boardSize;
	
	ImageIcon openImage;
	ImageIcon playerImage;
	ImageIcon computerImage;

	public ConnectFourPannel() {

		this.setTitle("Connect Four Game");

		int bSize = 10;
		String strBdSize;
		strBdSize = JOptionPane.showInputDialog(null, "Enter	board	size");
		bSize = Integer.parseInt(strBdSize);
		boardSize = bSize;
		game = new ConnectFourGame(bSize);

		this.setSize(800, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		openImage = new ImageIcon(new ImageIcon("OpenSpace.png").getImage().getScaledInstance(this.getWidth() / 10,
				this.getHeight() / 11, Image.SCALE_DEFAULT));
		
		playerImage = new ImageIcon(new ImageIcon("PlayerSpace.png").getImage().getScaledInstance(this.getWidth() / 10,
				this.getHeight() / 11, Image.SCALE_DEFAULT));
		
		computerImage = new ImageIcon(new ImageIcon("ComputerSpace.png").getImage()
				.getScaledInstance(this.getWidth() / 10, this.getHeight() / 11, Image.SCALE_DEFAULT));

		GridLayout layout = new GridLayout(bSize + 1, bSize);
		this.setLayout(layout);

		selection = new JButton[bSize];
		for (int x = 0; x < bSize; x++) {
			selection[x] = new JButton("Select");
			selection[x].addActionListener(this);
			this.add(selection[x]);
		}

		board = new JLabel[bSize][bSize];
		for (int y = 0; y < bSize; y++) {
			for (int x = 0; x < bSize; x++) {
				board[x][y] = new JLabel("");
				board[x][y].setIcon(openImage);
				board[x][y].setHorizontalAlignment(JLabel.CENTER);
				board[x][y].setVerticalAlignment(JLabel.CENTER);
				this.add(board[x][y]);
			}
		}

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		for (int num = 0; num < selection.length; num++) {
			if (obj == selection[num]) {

				// We draw from top down but need
				// to display from bottom up
				if (game.getPlayer() == 0) {

					game.selectCol(num);
					redraw();
					if (game.isWinner(0)) {
						JOptionPane.showMessageDialog(this, "You have won!");
					}
					game.switchPlayer();
					
					game.runAI();
					redraw();
					if (game.isWinner(1)) {
						JOptionPane.showMessageDialog(this, "You have lost!");
					}
					
					game.switchPlayer();
					displayAIVision();
					
					System.out.println(game.toString());
					
				}

			}
		}

	}
	
	public void redraw() {
		
		for (int y = 0; y < boardSize; y ++) {			
			for (int x = 0; x < boardSize; x ++) {

				if (game.getBoard()[x][y] == -1) {
					board[x][y].setIcon(openImage);
				}else if (game.getBoard()[x][y] == 0) {
					board[x][y].setIcon(playerImage);
				}else if (game.getBoard()[x][y] == 1) {
					board[x][y].setIcon(computerImage);
				}
			}
		}
		
	}
	
	public void displayAIVision() {
		
		int[][] boardScore = game.getBoardScore();
		
		for (int y = 0; y < boardSize; y ++) {
			for (int x = 0; x < boardSize; x ++) {
				
				board[x][y].setText(Integer.toString(boardScore[x][y]));
						
			}
		}
		
	}

}
