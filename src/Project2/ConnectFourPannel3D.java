package Project2;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConnectFourPannel3D extends JFrame implements ActionListener{

	private JLabel[][] board;
	/** GUI visual for the game board model */
	private JButton[] selection;
	/** buttons to select game columns */
	private ConnectFourGame3D game;
	/** reference to the game model */
	private int boardSize;
	private int currentBoard = 0;
	
	JButton leftBtn = new JButton("<--");
	JButton rightBtn = new JButton("-->");
	JLabel pageLbl = new JLabel("0 / " + Integer.toString(boardSize));
	
	ImageIcon openImage;
	ImageIcon playerImage;
	ImageIcon computerImage;

	public ConnectFourPannel3D() {

		this.setTitle("Connect Four Game");

		int bSize = 10;
		String strBdSize;
		strBdSize = JOptionPane.showInputDialog(null, "Enter board size");
		bSize = Integer.parseInt(strBdSize);
		boardSize = bSize;
		game = new ConnectFourGame3D(bSize);

		this.setSize(800, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		openImage = new ImageIcon(new ImageIcon("OpenSpace.png").getImage().getScaledInstance(this.getWidth() / bSize,
				this.getHeight() / (bSize + 2), Image.SCALE_DEFAULT));
		
		playerImage = new ImageIcon(new ImageIcon("PlayerSpace.png").getImage().getScaledInstance(this.getWidth() / bSize,
				this.getHeight() / (bSize + 2), Image.SCALE_DEFAULT));
		
		computerImage = new ImageIcon(new ImageIcon("ComputerSpace.png").getImage()
				.getScaledInstance(this.getWidth() / bSize, this.getHeight() / (bSize + 2), Image.SCALE_DEFAULT));

		GridLayout layout = new GridLayout(bSize + 2, bSize);
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
		
		leftBtn = new JButton("<--");
		rightBtn = new JButton("-->");
		pageLbl = new JLabel("1 / " + Integer.toString(bSize));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.ipadx = 100;
		
		leftBtn.addActionListener(this);
		rightBtn.addActionListener(this);
		
		this.add(leftBtn, c);
		this.add(rightBtn, c);
		
		pageLbl.setHorizontalAlignment(JLabel.CENTER);
		pageLbl.setVerticalAlignment(JLabel.CENTER);
		
		this.add(pageLbl, c);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		for(int num = 0; num < selection.length; num++){
			if (o == selection[num]){
				
				game.selectCol(currentBoard, num);
				fillBoard(currentBoard);
				
				if (game.isWinner(0)){
					JOptionPane.showMessageDialog(this, "You have won the game!");
				}
				
				game.switchPlayer();
				game.getBoard()[currentBoard].runAI();
				
				if (game.isWinner(1)){
					JOptionPane.showMessageDialog(this, "You have lost the game!");
				}
				
				game.switchPlayer();
				fillBoard(currentBoard);
				
			}
		}
		
		
		if (o == rightBtn){
			if (currentBoard + 1 < boardSize){
				currentBoard++;
			}else{
				currentBoard = 0;
			}
			
			fillBoard(currentBoard);
		}
		
		if (o == leftBtn){
			if (currentBoard - 1 >= 0){
				currentBoard--;
			}else{
				currentBoard = 4;
			}
			
			fillBoard(currentBoard);
		}
		
	}
	
	public void fillBoard(int row){
		
		for (int x = 0; x < boardSize; x ++){
			for (int y = 0; y < boardSize; y++){
				
				ConnectFourGame g = game.getBoard()[row]; 
				
				switch(g.getBoard()[x][y]){
				case -1:
					board[x][y].setIcon(openImage);
					break;
				case 0:
					board[x][y].setIcon(playerImage);
					break;
				case 1:
					board[x][y].setIcon(computerImage);
					break;
				}
				
			}
		}
		
		pageLbl.setText((currentBoard + 1) + " / " + boardSize);
		
	}



}
