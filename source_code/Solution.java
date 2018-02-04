package sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Solution extends JPanel{
	private Queue<Integer> moves;
	private int index, size;

	public Solution(Game game, Queue<Integer> prevMoves){
		JFrame frame = new JFrame();
		frame.setTitle("Solution");
		frame.setPreferredSize(new Dimension(600, 682));
		frame.setResizable(false);
		final Container container = frame.getContentPane();
		final Game solvedGame = new Game(null, null, 0, 0, 0, null, frame);

		// from https://stackoverflow.com/questions/21921135/using-setlocation-to-move-the-jframe-around-windows-java
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		int width = frame.getWidth();

		int east = screenWidth / 2;

		frame.setLocation(east, (screenHeight - 640) / 2);
		
		ImageIcon icon1 = new ImageIcon("img/tiles.png");
		JLabel bottomPanel1 = new JLabel();
		JLabel bottomPanel2 = new JLabel();
		JLabel bottomPanel3 = new JLabel();
		JLabel bottomPanel4 = new JLabel();
		JLabel bottomPanel5 = new JLabel();
		JLabel bottomPanel6 = new JLabel();
		JLabel bottomPanel7 = new JLabel();
		JLabel bottomPanel8 = new JLabel();
		JLabel bottomPanel9 = new JLabel();
		JLabel bottomPanel10 = new JLabel();
		bottomPanel1.setBounds(0, 600, 60, 60);
		bottomPanel2.setBounds(60, 600, 60, 60);
		bottomPanel3.setBounds(120, 600, 60, 60);
		bottomPanel4.setBounds(180, 600, 60, 60);
		bottomPanel5.setBounds(240, 600, 60, 60);
		bottomPanel6.setBounds(300, 600, 60, 60);
		bottomPanel7.setBounds(360, 600, 60, 60);
		bottomPanel8.setBounds(420, 600, 60, 60);
		bottomPanel9.setBounds(480, 600, 60, 60);
		bottomPanel10.setBounds(540, 600, 60, 60);
		bottomPanel1.setIcon(icon1);
		bottomPanel2.setIcon(icon1);
		bottomPanel3.setIcon(icon1);
		bottomPanel4.setIcon(icon1);
		bottomPanel5.setIcon(icon1);
		bottomPanel6.setIcon(icon1);
		bottomPanel7.setIcon(icon1);
		bottomPanel8.setIcon(icon1);
		bottomPanel9.setIcon(icon1);
		bottomPanel10.setIcon(icon1);


		this.size = game.getPrevMoves().size();
		for(int i = 0; i < size; i++){
			prevMoves.remove();
		}
		this.size = prevMoves.size();
		ArrayList<Integer> moves = new ArrayList<Integer>(prevMoves);
		moves.add(0);
		solvedGame.solution(game.getMap(), game.getPlayerX(), game.getPlayerY(), game.getVacantStorages(), true);

		this.index = 0;

		JButton button1 = new JButton();
		JButton button2 = new JButton();
		ImageIcon left = new ImageIcon("img/left.png");
		ImageIcon right = new ImageIcon("img/right.png");

		Solution sol = this;

		System.out.println("\nSolution (1 = UP; 2 = LEFT; 3 = DOWN; 4 = RIGHT):");
		System.out.println(moves);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(sol.index > 0){
					sol.index--;
					solvedGame.solution(game.getMap(), game.getPlayerX(), game.getPlayerY(), game.getVacantStorages(), false);
					for(int i = 0; i < index; i++){
						solvedGame.movePlayer(moves.get(i));
					}
				}
			}
		});

		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(sol.index < sol.size){
					solvedGame.movePlayer(moves.get(sol.index));
					sol.index++;
				}
			}
		});

		button1.setBounds(250, 610, 40, 40);
		button2.setBounds(310, 610, 40, 40);
		button1.setIcon(left);
		button2.setIcon(right);
		button1.setContentAreaFilled(false);
		button1.setFocusPainted(false);
		button1.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setFocusPainted(false);
		button2.setBorderPainted(false);
		frame.add(button1);
		frame.add(button2);


		frame.add(bottomPanel1);
		frame.add(bottomPanel2);
		frame.add(bottomPanel3);
		frame.add(bottomPanel4);
		frame.add(bottomPanel5);
		frame.add(bottomPanel6);
		frame.add(bottomPanel7);
		frame.add(bottomPanel8);
		frame.add(bottomPanel9);
		frame.add(bottomPanel10);
		container.add(solvedGame);

		frame.pack();
		frame.setVisible(true);
	}
}