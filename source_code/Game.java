package sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.Arrays;

public class Game extends JPanel{
	private int[][] map = new int[10][10];
	public String mapID, currentPath;
	private JLabel[] renderMap = new JLabel[100];
	private Queue<Integer> prevMoves = new LinkedList<Integer>();
	private JFrame frame;
	private JButton button, button1, button2;
	private JLabel label;
	private JPanel status;
	private Player player;
	private CardLayout layout;
	private Container container;
	private Game parentGame;
	private int vacantStorages, boxes, wallType, rotate;
	private boolean toDisplay, isSol;
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;
	public final static int BLOCK = 60;
	public final static int w = 1;
	public final static int e = 2;
	public final static int x = 3;
	public final static int b = 4;
	public final static int B = 5;
	public final static int k = 6;
	public final static int K = 7;
	public final static int s = 8;
	private final static ImageIcon BLUE_BOOK = new ImageIcon("img/blue_book.gif");
	private final static ImageIcon TRASH_BLUE_BOOK = new ImageIcon("img/trash_blue_book.gif");
	private final static ImageIcon TILES = new ImageIcon("img/tiles.png");
	private final static ImageIcon STUDENT_PNG = new ImageIcon("img/student.png");
	private final static ImageIcon STUDENT_GIF = new ImageIcon("img/student.gif");
	private final static ImageIcon TRASH = new ImageIcon("img/trash.png");
	private final static ImageIcon TRASH_STUDENT = new ImageIcon("img/trash_student.png");
	private final static ImageIcon WALL = new ImageIcon("img/wall.png");

	public Game(Game parent, int[][] map, int playerX, int playerY, int vacantStorages, Queue<Integer> prevMoves, JFrame frame){
		this.setLayout(null);
		this.wallType = 1;
		this.rotate = 0;
		this.toDisplay = false;
		this.isSol = false;
		if(map == null){
			this.vacantStorages = 0;
			this.boxes = 0;
			this.currentPath = "puzzle.in";
			this.frame = frame;
			this.readMap();
			// System.out.println("readMap");

			ImageIcon buttonIcon = new ImageIcon("img/game_button.png");
			JButton button9 = new JButton("BFS");
			this.button = button9;
			button9.setIcon(buttonIcon);
			button9.setBounds(51, 607, 144, 46);
			this.add(button9);
			button9.setContentAreaFilled(false);
			button9.setFocusPainted(false);
			button9.setBorderPainted(false);

			JButton button10 = new JButton("DFS");
			this.button1 = button10;
			button10.setIcon(buttonIcon);
			button10.setBounds(234, 607, 144, 46);
			this.add(button10);
			button10.setContentAreaFilled(false);
			button10.setFocusPainted(false);
			button10.setBorderPainted(false);

			JButton button11 = new JButton("File");
			this.button2 = button11;
			button11.setBounds(418, 607, 144, 46);
			button11.setIcon(buttonIcon);
			this.add(button11);
			button11.setContentAreaFilled(false);
			button11.setFocusPainted(false);
			button11.setBorderPainted(false);

			JLabel button_label1 = new JLabel();
			button_label1.setIcon(new ImageIcon("img/game_button_label1.png"));
			button9.add(button_label1);
			JLabel button_label2 = new JLabel();
			button_label2.setIcon(new ImageIcon("img/game_button_label2.png"));
			button10.add(button_label2);
			JLabel button_label3 = new JLabel();
			button_label3.setIcon(new ImageIcon("img/game_button_label3.png"));
			button11.add(button_label3);

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
			bottomPanel1.setIcon(TILES);
			bottomPanel2.setIcon(TILES);
			bottomPanel3.setIcon(TILES);
			bottomPanel4.setIcon(TILES);
			bottomPanel5.setIcon(TILES);
			bottomPanel6.setIcon(TILES);
			bottomPanel7.setIcon(TILES);
			bottomPanel8.setIcon(TILES);
			bottomPanel9.setIcon(TILES);
			bottomPanel10.setIcon(TILES);
			this.add(bottomPanel1);
			this.add(bottomPanel2);
			this.add(bottomPanel3);
			this.add(bottomPanel4);
			this.add(bottomPanel5);
			this.add(bottomPanel6);
			this.add(bottomPanel7);
			this.add(bottomPanel8);
			this.add(bottomPanel9);
			this.add(bottomPanel10);

			Game thisGame = this;
			button9.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					thisGame.moveFrame();

					System.out.println("\nFinding solution...");

					// from https://stackoverflow.com/questions/9458234/measuring-time-in-java
					long timeA = System.nanoTime();
					Game solvedGame = (new BruteForce(thisGame)).breadthFirstSearch();
					long timeB = System.nanoTime();
					System.out.println("Elapsed time (BFS): " + (timeB - timeA) + " nanoseconds.");
					solvedGame.writeMoves();
					Solution sol = new Solution(thisGame, solvedGame.getPrevMoves());
					thisGame.requestFocus();
				}
			});
			button10.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					thisGame.moveFrame();

					System.out.println("\nFinding solution...");
					
					long timeA = System.nanoTime();
					Game solvedGame = (new BruteForce(thisGame)).depthFirstSearch();
					long timeB = System.nanoTime();
					System.out.println("Elapsed time (DFS): " + (timeB - timeA) + " nanoseconds.");
					solvedGame.writeMoves();
					Solution sol = new Solution(thisGame, solvedGame.getPrevMoves());
					thisGame.requestFocus();
				}
			});
			button11.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// from https://www.mkyong.com/swing/java-swing-jfilechooser-example/
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setDialogTitle("Select Puzzle File");
					jfc.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".in files" , "in");
					System.out.println(jfc.getCurrentDirectory());
					jfc.addChoosableFileFilter(filter);
					int returnValue = jfc.showOpenDialog(null);
					// int returnValue = jfc.showSaveDialog(null);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						thisGame.currentPath = selectedFile.getAbsolutePath();
						thisGame.resetGame();
					}
					thisGame.requestFocus();
				}
			});
		} else {
			this.map = map;
			this.player = new Player(playerX, playerY, this, false);
			this.vacantStorages = vacantStorages;
			this.prevMoves = prevMoves;
			this.parentGame = parent;
		}
		this.getMapID();
	}

	public void solution(int[][] map, int playerX, int playerY, int vacantStorages, boolean toRenderInitial){
		this.map = map;
		this.player.setXY(playerX, playerY);
		this.vacantStorages = vacantStorages;
		this.player.solution();
		this.isSol = true;
		if(toRenderInitial){
			this.renderInitial();
			this.remove(this.button);
			this.remove(this.button1);
			this.remove(this.button2);
		} else {
			this.renderAll();
		}

	}

	public boolean isSolution(){
		return this.isSol;
	}

	public void display(Container container, CardLayout layout, JPanel status, JLabel label){
		this.toDisplay = true;
		this.label = label;
		this.status = status;
		this.layout = layout;
		this.container = container;
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

	private void moveFrame(){
		// from https://stackoverflow.com/questions/21921135/using-setlocation-to-move-the-jframe-around-windows-java
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		int width = this.frame.getWidth();
		int height = this.frame.getHeight();

		int north = (screenHeight - height) / 2;
		int east = (screenWidth / 2) - width;
		this.frame.setLocation(east, north);
	}

	public boolean isDisplay(){
		return this.toDisplay;
	}

	public void printMap(){
		System.out.println();
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				if(this.map[row][col] == k){
					System.out.print("k ");
				} else if(this.map[row][col] == K){
					System.out.print("K ");
				} else if(this.map[row][col] == b){
					System.out.print("b ");
				} else if(this.map[row][col] == B){
					System.out.print("B ");
				} else if(this.map[row][col] == s){
					System.out.print("s ");
				} else if(this.map[row][col] == e){
					System.out.print("e ");
				} else if(this.map[row][col] == w){
					System.out.print("w ");
				} else if(this.map[row][col] == x){
					System.out.print("x ");
				}
			}
			System.out.println();
		}
		System.out.println("\nPress spacebar for game stats\nPress R to restart game");
	}

	public void addMove(int direction){
		this.prevMoves.add(direction);
		if(this.toDisplay){
			System.out.println(this.prevMoves);
		}
	}

	public void incStorage(){
		this.vacantStorages++;
	}

	public void decStorage(){
		this.vacantStorages--;
	}

	public int getItem(int x, int y){
		return this.map[y][x];
	}

	private void getMapID(){
		this.mapID = Arrays.deepToString(this.map);
	}

	private void readMap(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(this.currentPath));
			String line;
			int i = 0;
			while((line = reader.readLine()) != null) {
				String[] letters = line.split(" ");
				for(int j = 0; j < 10; j++){
					if(letters[j].equals("k")){
						this.map[i][j] = k;
						this.player = new Player(j, i, this, true);
					} else if(letters[j].equals("s") || letters[j].equals("K")){
						this.vacantStorages++;
						if(letters[j].equals("s")){
							this.map[i][j] = s;
						} else {
							this.map[i][j] = K;
						}
					} else if(letters[j].equals("b")){
						this.boxes++;
						this.map[i][j] = b;
					} else if(letters[j].equals("B")){
						this.map[i][j] = B;
					} else if(letters[j].equals("w")){
						this.map[i][j] = w;
					} else if(letters[j].equals("e")){
						this.map[i][j] = e;
					} else if(letters[j].equals("x")){
						this.map[i][j] = x;
					}
				}
				i++;
			}
			// this.printMap();
		} catch(FileNotFoundException e){
			System.out.println("File puzzle.in not found");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(this.vacantStorages < this.boxes){
			System.out.println("\nWarning! No. of boxes is more than the no. of storages");
		} else if(this.vacantStorages > this.boxes){
			System.out.println("\nWarning! No. of boxes is less than the no. of storages");
		}
	}

	public void writeMoves(){
		try{
			Queue<Integer> moves = this.getPrevMoves();
			int size = moves.size();
			int direction;
			String text = "";
			for(int i = 0; i < size; i++){
				direction = moves.remove();
				if(direction == UP){
					text = text + "U ";
				} else if(direction == LEFT){
					text = text + "L ";
				} else if(direction == DOWN){
					text = text + "D ";
				} else if(direction == RIGHT){
					text = text + "R ";
				}
			}
			PrintWriter writer = new PrintWriter("puzzle.out", "UTF-8");
			writer.println(text);
			writer.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void moveItem(int mapX, int mapY, int direction, int letter1, int letter2){
		if(direction == UP){
			this.map[mapY - 1][mapX] = letter1;
			this.map[mapY][mapX] = letter2;
		} else if(direction == LEFT){
			this.map[mapY][mapX - 1] = letter1;
			this.map[mapY][mapX] = letter2;
		} else if(direction == DOWN){
			this.map[mapY + 1][mapX] = letter1;
			this.map[mapY][mapX] = letter2;
		} else if(direction == RIGHT){
			this.map[mapY][mapX + 1] = letter1;
			this.map[mapY][mapX] = letter2;
		}
		this.getMapID();
	}

	public void renderInitial(){
		ImageIcon wall_type = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
		ImageIcon student_type;
		if(this.rotate == 1){
			student_type = STUDENT_GIF;
		} else {
			student_type = STUDENT_PNG;
		}
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j] == b){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(this.BLUE_BOOK);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == B){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(this.TRASH_BLUE_BOOK);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == e || this.map[i][j] == x){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(this.TILES);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == k){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(student_type);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == s){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(this.TRASH);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == w){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(wall_type);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				} else if(this.map[i][j] == K){
					JLabel test = new JLabel();
					test.setBounds(j * BLOCK, i * BLOCK, BLOCK, BLOCK);
					test.setIcon(this.TRASH_STUDENT);
					this.add(test);
					this.renderMap[(i * 10) + j] = test;
				}
			}
		}
	}

	public void renderAll(){
		ImageIcon wall_type = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
		ImageIcon student_type;
		if(this.rotate == 1){
			student_type = STUDENT_GIF;
		} else {
			student_type = STUDENT_PNG;
		}
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j] == b){
					this.renderMap[(i * 10) + j].setIcon(BLUE_BOOK);
				} else if(this.map[i][j] == B){
					this.renderMap[(i * 10) + j].setIcon(this.TRASH_BLUE_BOOK);
				} else if(this.map[i][j] == e || this.map[i][j] == x){
					this.renderMap[(i * 10) + j].setIcon(this.TILES);
				} else if(this.map[i][j] == k){
					this.renderMap[(i * 10) + j].setIcon(student_type);
				} else if(this.map[i][j] == s){
					this.renderMap[(i * 10) + j].setIcon(this.TRASH);
				} else if(this.map[i][j] == w){
					this.renderMap[(i * 10) + j].setIcon(wall_type);
				} else if(this.map[i][j] == K){
					this.renderMap[(i * 10) + j].setIcon(this.TRASH_STUDENT);
				}
			}
		}
	}

	public void renderTiles(int mapX, int mapY, int direction){
		ImageIcon student_type;
		if(this.rotate == 1){
			student_type = STUDENT_GIF;
		} else {
			student_type = STUDENT_PNG;
		}
		if(!this.isSol){
			this.label.setText("Moves: " + this.prevMoves.size());
		}
		if(mapY < 10 && direction == UP){
			mapY++;
		} else if(mapX < 10 && direction == LEFT){
			mapX++;
		} else if(mapY >=0 && direction == DOWN){
			mapY--;
		} else if(mapX >=0 && direction == RIGHT){
			mapX--;
		}
		for(int i = 0; i < 3; i++){
			if(this.map[mapY][mapX] == b){
				this.renderMap[(mapY * 10) + mapX].setIcon(this.BLUE_BOOK);
			} else if(this.map[mapY][mapX] == B){
				this.renderMap[(mapY * 10) + mapX].setIcon(this.TRASH_BLUE_BOOK);
			} else if(this.map[mapY][mapX] == e || this.map[mapY][mapX] == x){
				this.renderMap[(mapY * 10) + mapX].setIcon(this.TILES);
			} else if(this.map[mapY][mapX] == k){
				this.renderMap[(mapY * 10) + mapX].setIcon(student_type);
			} else if(this.map[mapY][mapX] == s){
				this.renderMap[(mapY * 10) + mapX].setIcon(this.TRASH);
			} else if(this.map[mapY][mapX] == K){
				this.renderMap[(mapY * 10) + mapX].setIcon(this.TRASH_STUDENT);
			}
			if(mapY >=0 && direction == UP){
				mapY--;
			} else if(mapX >= 0 && direction == LEFT){
				mapX--;
			} else if(mapY < 10 && direction == DOWN){
				mapY++;
			} else if(mapX < 10 && direction == RIGHT){
				mapX++;
			} else {
				break;
			}
		}
	}

	public void checkWin(){
		if(this.vacantStorages == 0 && this.prevMoves.size() > 0){
			this.layout.show(this.container, "status");
			this.status.requestFocus();
		}
	}

	public void renderWalls(){
		ImageIcon icon = new ImageIcon("img/wall" + Integer.toString(this.wallType) + ".png");
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(this.map[i][j] == w){
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

	public int[][] getMap(){
		int[][] newMap = new int[10][10];
		for(int i = 0; i < 10; i++){
			System.arraycopy(this.map[i], 0, newMap[i], 0, 10);
		}
		return newMap;
	}

	public Queue<Integer> getValidMoves(){
		Queue<Integer> validMoves = new LinkedList<Integer>();
		if(this.player.isValidMove(1)){
			validMoves.add(1);
		}
		if(this.player.isValidMove(2)){
			validMoves.add(2);
		}
		if(this.player.isValidMove(3)){
			validMoves.add(3);
		}
		if(this.player.isValidMove(4)){
			validMoves.add(4);
		}
		return validMoves;
	}

	public boolean isFinished(){
		if(this.vacantStorages == 0 && this.prevMoves.size() > 0){
			return true;
		} else {
			return false;
		}
	}

	public int getVacantStorages(){
		return this.vacantStorages;
	}

	public int getPlayerX(){
		return this.player.getX();
	}

	public int getPlayerY(){
		return this.player.getY();
	}

	public void movePlayer(int direction){
		this.player.move(direction);
	}

	public Game getParentGame(){
		return this.parentGame;
	}

	public int getCost(Queue<Integer> path){
		int size = path.size();
		for(int i = 0; i < size; i++){
			this.player.move(path.remove());
		}
		return this.prevMoves.size();
	}

	public Queue<Integer> getPrevMoves(){
		return new LinkedList<Integer>(this.prevMoves);
	}

	public Game getResult(int direction){
		Game newGame = new Game(this, this.getMap(), this.player.getX(), this.player.getY(), this.vacantStorages, new LinkedList<Integer>(this.prevMoves), null);
		newGame.player.move(direction);
		return newGame;
	}

	public void reenact(Queue<Integer> prevMoves){
		int size = prevMoves.size();
		for(int i = 0; i < size; i++){
			// From https://stackoverflow.com/questions/24104313/how-to-delay-in-java
			// try {
			//     Thread.sleep(1000);
			// } catch(InterruptedException e) {
			//     Thread.currentThread().interrupt();
			// }
			this.player.move(prevMoves.remove());
		}
	}

	public void resetGame(){
		this.vacantStorages = 0;
		this.boxes = 0;
		this.label.setText("Moves: 0");
		this.readMap();
		this.renderAll();
		int size = this.prevMoves.size();
		for(int i = 0; i < size; i++){
			this.prevMoves.remove();
		}
		this.getMapID();
	}
}