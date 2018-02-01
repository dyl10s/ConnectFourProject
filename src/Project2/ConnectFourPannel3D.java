package Project2;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFourPannel3D extends JFrame implements ActionListener{

	private JLabel[][][] board;
	/** GUI visual for the game board model */
	private JButton[][] selection;
	/** buttons to select game columns */
	private ConnectFourGame3D game;
	/** reference to the game model */
	private int boardSize;
	
	ImageIcon openImage;
	ImageIcon playerImage;
	ImageIcon computerImage;
	
	public ConnectFourPannel3D(){
		
		this.setTitle("Connect Four Game");

		int bSize = 10;
		String strBdSize;
		strBdSize = JOptionPane.showInputDialog(null, "Enter	board	size");
		bSize = Integer.parseInt(strBdSize);
		boardSize = bSize;
		game = new ConnectFourGame3D(bSize);

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
		
		board = new JLabel[boardSize][boardSize][boardSize];
		
		JPanel[] boardSlice = new JPanel[boardSize];
		
		this.setLayout(null);
		
		for (int z = 0; z < boardSize; z++){
			
			boardSlice[z] = new JPanel();
			boardSlice[z].setPreferredSize(new Dimension(50, 50));
			
			for(int y = 0; y < boardSize; y ++){
				for (int x = 0; x < boardSize; x++){
					
					board[x][y][z] = new JLabel();
					//board[x][y][z].setIcon(openImage);
					board[x][y][z].setText("X");
					boardSlice[z].add(board[x][y][z]);
					
				}
			}
			
			this.add(boardSlice[z]);
			boardSlice[z].setLocation(z * 50, 0);
		}
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		
	}



}
