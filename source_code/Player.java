package sokoban;

import javax.swing.*;
import java.awt.*;

public class Player {
	private Game game;
	private int x, y;
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;

	public Player(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
	}

	private void setX(int value){
		if(this.x > value){
			this.game.addMove(LEFT);
		} else {
			this.game.addMove(RIGHT);
		}
		this.x = value;
		this.game.incMoves();
	}

	private void setY(int value){
		if(this.y > value){
			this.game.addMove(UP);
		} else {
			this.game.addMove(DOWN);
		}
		this.y = value;
		this.game.incMoves();
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void move(int direction){
		if(direction == UP){
			if(this.y > 0){
				if(this.game.getItem(this.x, this.y - 1).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, UP, "k", "e");
						this.setY(this.y - 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, UP, "k", "s");
						this.setY(this.y - 1);
					}
				} else if(this.game.getItem(this.x, this.y - 1).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, UP, "K", "e");
						this.setY(this.y - 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, UP, "K", "s");
						this.setY(this.y - 1);
					}
				} else if(this.game.getItem(this.x, this.y - 1).equals("w")){
					System.out.println("You're being blocked by a wall.");
				} else if(this.game.getItem(this.x, this.y - 1).equals("b") || this.game.getItem(this.x, this.y - 1).equals("B")){
					if(this.game.getItem(this.x, this.y - 2).equals("b") || this.game.getItem(this.x, this.y - 2).equals("B")){
						System.out.println("Blue book is being blocked by another blue book.");
					} else if(this.game.getItem(this.x, this.y - 2).equals("w")){
						System.out.println("Blue book is being blocked by a wall.");
					} else if(this.game.getItem(this.x, this.y - 2).equals("s")){
						if(this.game.getItem(this.x, this.y - 1).equals("B")){
							this.game.moveItem(this.x, this.y - 1, UP, "B", "s");
						} else if(this.game.getItem(this.x, this.y - 1).equals("b")){
							this.game.moveItem(this.x, this.y - 1, UP, "B", "e");
							this.game.decStorage();
						}

						if(this.game.getItem(this.x, this.y - 1).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, UP, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, UP, "k", "s");
							}
						} else if(this.game.getItem(this.x, this.y - 1).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, UP, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, UP, "K", "s");
							}
						}
						this.setY(this.y - 1);
					} else if(this.game.getItem(this.x, this.y - 2).equals("e")){
						if(this.game.getItem(this.x, this.y - 1).equals("B")){
							this.game.moveItem(this.x, this.y - 1, UP, "b", "s");
							this.game.incStorage();
						} else if(this.game.getItem(this.x, this.y - 1).equals("b")){
							this.game.moveItem(this.x, this.y - 1, UP, "b", "e");
						}

						if(this.game.getItem(this.x, this.y - 1).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, UP, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, UP, "k", "s");
							}
						} else if(this.game.getItem(this.x, this.y - 1).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, UP, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, UP, "K", "s");
							}
						}
						this.setY(this.y - 1);
					}
				}
			}
			this.game.renderTiles(this.x, this.y, UP);
		} else if(direction == LEFT){
			if(this.x > 0){
				if(this.game.getItem(this.x - 1, this.y).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, LEFT, "k", "e");
						this.setX(this.x - 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, LEFT, "k", "s");
						this.setX(this.x - 1);
					}
				} else if(this.game.getItem(this.x - 1, this.y).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, LEFT, "K", "e");
						this.setX(this.x - 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, LEFT, "K", "s");
						this.setX(this.x - 1);
					}
				} else if(this.game.getItem(this.x - 1, this.y).equals("w")){
					System.out.println("\nYou're being blocked by a wall.");
				} else if(this.game.getItem(this.x - 1, this.y).equals("b") || this.game.getItem(this.x - 1, this.y).equals("B")){
					if(this.game.getItem(this.x - 2, this.y).equals("b") || this.game.getItem(this.x - 2, this.y).equals("B")){
						System.out.println("\nBlue book is being blocked by another blue book.");
					} else if(this.game.getItem(this.x - 2, this.y).equals("w")){
						System.out.println("\nBlue book is being blocked by a wall.");
					} else if(this.game.getItem(this.x - 2, this.y).equals("s")){
						if(this.game.getItem(this.x - 1, this.y).equals("B")){
							this.game.moveItem(this.x - 1, this.y, LEFT, "B", "s");
						} else if(this.game.getItem(this.x - 1, this.y).equals("b")){
							this.game.moveItem(this.x - 1, this.y, LEFT, "B", "e");
							this.game.decStorage();
						}

						if(this.game.getItem(this.x - 1, this.y).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, LEFT, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, LEFT, "k", "s");
							}
						} else if(this.game.getItem(this.x - 1, this.y).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, LEFT, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, LEFT, "K", "s");
							}
						}
						this.setX(this.x - 1);
					} else if(this.game.getItem(this.x - 2, this.y).equals("e")){
						if(this.game.getItem(this.x - 1, this.y).equals("B")){
							this.game.moveItem(this.x - 1, this.y, LEFT, "b", "s");
							this.game.incStorage();
						} else if(this.game.getItem(this.x - 1, this.y).equals("b")){
							this.game.moveItem(this.x - 1, this.y, LEFT, "b", "e");
						}

						if(this.game.getItem(this.x - 1, this.y).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, LEFT, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, LEFT, "k", "s");
							}
						} else if(this.game.getItem(this.x - 1, this.y).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, LEFT, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, LEFT, "K", "s");
							}
						}
						this.setX(this.x - 1);
					}
				}
			}
			this.game.renderTiles(this.x, this.y, LEFT);
		} else if(direction == DOWN){
			if(this.y < 10){
				if(this.game.getItem(this.x, this.y + 1).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, DOWN, "k", "e");
						this.setY(this.y + 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, DOWN, "k", "s");
						this.setY(this.y + 1);
					}
				} else if(this.game.getItem(this.x, this.y + 1).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, DOWN, "K", "e");
						this.setY(this.y + 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, DOWN, "K", "s");
						this.setY(this.y + 1);
					}
				} else if(this.game.getItem(this.x, this.y + 1).equals("w")){
					System.out.println("You're being blocked by a wall.");
				} else if(this.game.getItem(this.x, this.y + 1).equals("b") || this.game.getItem(this.x, this.y + 1).equals("B")){
					if(this.game.getItem(this.x, this.y + 2).equals("b") || this.game.getItem(this.x, this.y + 2).equals("B")){
						System.out.println("Blue book is being blocked by another blue book.");
					} else if(this.game.getItem(this.x, this.y + 2).equals("w")){
						System.out.println("Blue book is being blocked by a wall.");
					} else if(this.game.getItem(this.x, this.y + 2).equals("s")){
						if(this.game.getItem(this.x, this.y + 1).equals("B")){
							this.game.moveItem(this.x, this.y + 1, DOWN, "B", "s");
						} else if(this.game.getItem(this.x, this.y + 1).equals("b")){
							this.game.moveItem(this.x, this.y + 1, DOWN, "B", "e");
							this.game.decStorage();
						}

						if(this.game.getItem(this.x, this.y + 1).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, DOWN, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, DOWN, "k", "s");
							}
						} else if(this.game.getItem(this.x, this.y + 1).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, DOWN, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, DOWN, "K", "s");
							}
						}
						this.setY(this.y + 1);
					} else if(this.game.getItem(this.x, this.y + 2).equals("e")){
						if(this.game.getItem(this.x, this.y + 1).equals("B")){
							this.game.moveItem(this.x, this.y + 1, DOWN, "b", "s");
							this.game.incStorage();
						} else if(this.game.getItem(this.x, this.y + 1).equals("b")){
							this.game.moveItem(this.x, this.y + 1, DOWN, "b", "e");
						}

						if(this.game.getItem(this.x, this.y + 1).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, DOWN, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, DOWN, "k", "s");
							}
						} else if(this.game.getItem(this.x, this.y + 1).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, DOWN, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, DOWN, "K", "s");
							}
						}
						this.setY(this.y + 1);
					}
				}
			}
			this.game.renderTiles(this.x, this.y, DOWN);
		} else if(direction == RIGHT){
			if(this.x < 10){
				if(this.game.getItem(this.x + 1, this.y).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, RIGHT, "k", "e");
						this.setX(this.x + 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, RIGHT, "k", "s");
						this.setX(this.x + 1);
					}
				} else if(this.game.getItem(this.x + 1, this.y).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, RIGHT, "K", "e");
						this.setX(this.x + 1);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, RIGHT, "K", "s");
						this.setX(this.x + 1);
					}
				} else if(this.game.getItem(this.x + 1, this.y).equals("w")){
					System.out.println("You're being blocked by a wall.");
				} else if(this.game.getItem(this.x + 1, this.y).equals("b") || this.game.getItem(this.x + 1, this.y).equals("B")){
					if(this.game.getItem(this.x + 2, this.y).equals("b") || this.game.getItem(this.x + 2, this.y).equals("B")){
						System.out.println("Blue book is being blocked by another blue book.");
					} else if(this.game.getItem(this.x + 2, this.y).equals("w")){
						System.out.println("Blue book is being blocked by a wall.");
					} else if(this.game.getItem(this.x + 2, this.y).equals("s")){
						if(this.game.getItem(this.x + 1, this.y).equals("B")){
							this.game.moveItem(this.x + 1, this.y, RIGHT, "B", "s");
						} else if(this.game.getItem(this.x + 1, this.y).equals("b")){
							this.game.moveItem(this.x + 1, this.y, RIGHT, "B", "e");
							this.game.decStorage();
						}

						if(this.game.getItem(this.x + 1, this.y).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, RIGHT, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, RIGHT, "k", "s");
							}
						} else if(this.game.getItem(this.x + 1, this.y).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, RIGHT, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, RIGHT, "K", "s");
							}
						}
						this.setX(this.x + 1);
					} else if(this.game.getItem(this.x + 2, this.y).equals("e")){
						if(this.game.getItem(this.x + 1, this.y).equals("B")){
							this.game.moveItem(this.x + 1, this.y, RIGHT, "b", "s");
							this.game.incStorage();
						} else if(this.game.getItem(this.x + 1, this.y).equals("b")){
							this.game.moveItem(this.x + 1, this.y, RIGHT, "b", "e");
						}

						if(this.game.getItem(this.x + 1, this.y).equals("e")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, RIGHT, "k", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, RIGHT, "k", "s");
							}
						} else if(this.game.getItem(this.x + 1, this.y).equals("s")){
							if(this.game.getItem(this.x, this.y).equals("k")){
								this.game.moveItem(this.x, this.y, RIGHT, "K", "e");
							} else if(this.game.getItem(this.x, this.y).equals("K")){
								this.game.moveItem(this.x, this.y, RIGHT, "K", "s");
							}
						}
						this.setX(this.x + 1);
					}
				}
			}
			this.game.renderTiles(this.x, this.y, RIGHT);
			this.game.checkWin();
		}
		this.game.printMap();
	}
}