package sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.util.ArrayList;

public class Game extends JPanel{
	private String[][] map = new String[10][10];
	private JLabel[] renderMap = new JLabel[100];
	private ArrayList<Integer> prevMoves = new ArrayList<Integer>();
	private JLabel label;
	private JPanel status;
	private Player player;
	private CardLayout layout;
	private Container container;
	private int vacantStorages, boxes, moves, wallType, rotate;
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;
	public final static int BLOCK = 60;

	public Game(Container container, CardLayout layout, JPanel status, JLabel label){
		this.setLayout(null);
		this.wallType = 1;
		this.rotate = 0;
		this.label = label;
		this.status = status;
		this.layout = layout;
		this.container = container;
		this.vacantStorages = 0;
		this.boxes = 0;
		this.moves = 0;
		this.readMap();
		this.renderInitial();
		Game game = this;
		this.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_UP){
					game.player.move(UP);
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					game.player.move(LEFT);
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					game.player.move(DOWN);
				} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					game.player.move(RIGHT);
				}
			}
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
		});
	}

	public void printMap(){
		System.out.println();
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				System.out.print(this.map[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println("\nPress spacebar for game stats\nPress R to restart game");
	}

	public void incMoves(){
		this.moves++;
		this.label.setText("Moves: " + this.moves);
	}

	public void addMove(int direction){
		this.prevMoves.add(direction);
		System.out.println(this.prevMoves);
	}

	public void incStorage(){
		this.vacantStorages++;
	}

	public void decStorage(){
		this.vacantStorages--;
	}

	public String getItem(int x, int y){
		return this.map[y][x];
	}

	private void readMap(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("source_code/puzzle.in"));
			String line;
			int i = 0;
			while((line = reader.readLine()) != null) {
				String[] letters = line.split(" ");
				for(int j = 0; j < 10; j++){
					this.map[i][j] = letters[j];
					if(this.map[i][j].equals("k")){
						this.player = new Player(j, i, this);
					} else if(this.map[i][j].equals("s") || this.map[i][j].equals("K")){
						this.vacantStorages++;
					} else if(this.map[i][j].equals("b")){
						this.boxes++;
					}
				}
				i++;
			}
			this.printMap();
		}catch(FileNotFoundException e){
			System.out.println("File puzzle.in not found");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(this.vacantStorages < this.boxes){
			System.out.println("\nWarning! No. of boxes is more than the no. of storages");
		} else if(this.vacantStorages > this.boxes){
			System.out.println("\nWarning! No. of boxes is less than the no. of storages");
		}
	}

	public void moveItem(int x, int y, int direction, String letter1, String letter2){
		if(direction == UP){
			this.map[y - 1][x] = letter1;
			this.map[y][x] = letter2;
		} else if(direction == LEFT){
			this.map[y][x - 1] = letter1;
			this.map[y][x] = letter2;
		} else if(direction == DOWN){
			this.map[y + 1][x] = letter1;
			this.map[y][x] = letter2;
		} else if(direction == RIGHT){
			this.map[y][x + 1] = letter1;
			this.map[y][x] = letter2;
		}
	}

	public void renderInitial(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j].equals("b")){
					ImageIcon icon = new ImageIcon("img/blue_book.gif");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("B")){
					ImageIcon icon = new ImageIcon("img/trash_blue_book.gif");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("e") || this.map[i][j].equals("x")){
					ImageIcon icon = new ImageIcon("img/tiles.png");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("k")){
					ImageIcon icon;
					if(this.rotate == 1){
						icon = new ImageIcon("img/student.gif");
					} else {
						icon = new ImageIcon("img/student.png");
					}
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("s")){
					ImageIcon icon = new ImageIcon("img/trash.png");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("w")){
					ImageIcon icon = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j].equals("K")){
					ImageIcon icon = new ImageIcon("img/trash_student.png");
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(icon);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				}
			}
		}
	}

	public void renderAll(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j].equals("b")){
					ImageIcon icon = new ImageIcon("img/blue_book.gif");
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("B")){
					ImageIcon icon = new ImageIcon("img/trash_blue_book.gif");
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("e") || this.map[i][j].equals("x")){
					ImageIcon icon = new ImageIcon("img/tiles.png");
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("k")){
					ImageIcon icon;
					if(this.rotate == 1){
						icon = new ImageIcon("img/student.gif");
					} else {
						icon = new ImageIcon("img/student.png");
					}
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("s")){
					ImageIcon icon = new ImageIcon("img/trash.png");
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("w")){
					ImageIcon icon = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
					this.renderMap[(i * 10) + j].setIcon(icon);
				} else if(this.map[i][j].equals("K")){
					ImageIcon icon = new ImageIcon("img/trash_student.png");
					this.renderMap[(i * 10) + j].setIcon(icon);
				}
			}
		}
	}

	public void renderTiles(int x, int y, int direction){
		if(y < 10 && direction == UP){
			y++;
		} else if(x < 10 && direction == LEFT){
			x++;
		} else if(y >=0 && direction == DOWN){
			y--;
		} else if(x >=0 && direction == RIGHT){
			x--;
		}
		for(int i = 0; i < 3; i++){
			if(this.map[y][x].equals("b")){
				ImageIcon icon = new ImageIcon("img/blue_book.gif");
				this.renderMap[(y * 10) + x].setIcon(icon);
			} else if(this.map[y][x].equals("B")){
				ImageIcon icon = new ImageIcon("img/trash_blue_book.gif");
				this.renderMap[(y * 10) + x].setIcon(icon);
			} else if(this.map[y][x].equals("e") || this.map[y][x].equals("x")){
				ImageIcon icon = new ImageIcon("img/tiles.png");
				this.renderMap[(y * 10) + x].setIcon(icon);
			} else if(this.map[y][x].equals("k")){
				ImageIcon icon;
				if(this.rotate == 1){
					icon = new ImageIcon("img/student.gif");
				} else {
					icon = new ImageIcon("img/student.png");
				}
				this.renderMap[(y * 10) + x].setIcon(icon);
			} else if(this.map[y][x].equals("s")){
				ImageIcon icon = new ImageIcon("img/trash.png");
				this.renderMap[(y * 10) + x].setIcon(icon);
			} else if(this.map[y][x].equals("K")){
				ImageIcon icon = new ImageIcon("img/trash_student.png");
				this.renderMap[(y * 10) + x].setIcon(icon);
			}
			if(y >=0 && direction == UP){
				y--;
			} else if(x >= 0 && direction == LEFT){
				x--;
			} else if(y < 10 && direction == DOWN){
				y++;
			} else if(x < 10 && direction == RIGHT){
				x++;
			} else {
				break;
			}
		}
	}

	public void checkWin(){
		if(this.vacantStorages == 0 && moves > 0){
			this.layout.show(this.container, "status");
			this.status.requestFocus();
		}
	}

	public void renderWalls(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j].equals("w")){
					ImageIcon icon = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
					this.renderMap[(i * 10) + j].setIcon(icon);
				}
			}
		}
	}

	public void changeWalls(){
		if(this.wallType < 4){
			this.wallType++;
		} else {
			this.wallType = 1;
		}
		this.renderWalls();
	}

	public void rotateStudent(){
		if(this.rotate == 0){
			this.rotate++;
		} else {
			this.rotate--;
		}
		this.renderTiles(this.player.getX(), this.player.getY(), UP);
	}

	public void resetGame(){
		this.moves = 0;
		this.vacantStorages = 0;
		this.boxes = 0;
		this.label.setText("Moves: " + this.moves);
		this.readMap();
		this.renderAll();
		this.prevMoves.clear();
	}
}