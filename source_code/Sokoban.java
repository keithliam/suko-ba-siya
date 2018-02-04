package sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Sokoban{
	public Sokoban(){
		JFrame frame = new JFrame("Sokoban");
		frame.setPreferredSize(new Dimension(600, 682));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Container container = frame.getContentPane();
		container.setLayout(new CardLayout());
		final CardLayout layout = (CardLayout)(container.getLayout());

		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.BLACK);
		menu.setPreferredSize(new Dimension(600, 660));


		JButton button = new JButton();
		ImageIcon icon3 = new ImageIcon("img/start_button.gif");

		button.setBounds(196, 421, 210, 47);
		button.setIcon(icon3);
		menu.add(button);


		ImageIcon icon1 = new ImageIcon("img/menu.gif");
		JLabel menuImage = new JLabel();
		menuImage.setBounds(0, 0, 600, 660);
		menuImage.setIcon(icon1);
		menu.add(menuImage);

		JPanel status = new JPanel();
		status.setLayout(null);
		status.setPreferredSize(new Dimension(600, 660));


		ImageIcon icon2 = new ImageIcon("img/pause.gif");
		JLabel pauseImage = new JLabel();
		pauseImage.setBounds(0, 0, 600, 660);
		pauseImage.setIcon(icon2);


		JLabel movesLabel = new JLabel("Moves: 0");
		movesLabel.setBounds(255, 435, 150, 20);
		movesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		movesLabel.setForeground(Color.WHITE);

		status.add(movesLabel);
		status.add(pauseImage);

		final Game game = new Game(null, null, 0, 0, 0, null);
		game.display(container, layout, status, movesLabel);


		container.add(menu, "menu");
		container.add(game, "game");
		container.add(status, "status");

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				layout.show(container, "game");
				game.requestFocus();
			}
		});

		game.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P){
					layout.show(container, "status");
					status.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_R){
					game.resetGame();
				} else if(e.getKeyCode() == KeyEvent.VK_W){
					game.changeWalls();
				} else if(e.getKeyCode() == KeyEvent.VK_O){
					game.rotateStudent();
				} else if(e.getKeyCode() == KeyEvent.VK_Q){
					game.resetGame();
					layout.show(container, "menu");
					menu.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_S){
					// from https://stackoverflow.com/questions/9458234/measuring-time-in-java
					System.out.println("Finding solution...");
					long timeA = System.currentTimeMillis();
					Game solvedGame = (new BruteForce(game)).depthFirstSearch();
					long timeB = System.currentTimeMillis();
					// if(solvedGame != null){
					// 	game.resetGame();
					// 	game.reenact(solvedGame.getPrevMoves());
					// }
					if(solvedGame != null){
						solvedGame.writeMoves();
					}
					System.out.println("\nElapsed time: " + (timeB - timeA) + " milliseconds.");
					if(solvedGame != null){
						Solution sol = new Solution(game, solvedGame.getPrevMoves());
					}
					// solvedGame.reenact();
				}
			}
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){
			}
		});

		status.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P){
					layout.show(container, "game");
					game.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_R){
					game.resetGame();
					layout.show(container, "game");
					game.requestFocus();
				} else if(e.getKeyCode() == KeyEvent.VK_Q){
					game.resetGame();
					layout.show(container, "menu");
					menu.requestFocus();
				}
			}
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
		});

		frame.pack();
		frame.setVisible(true);
	}
}
