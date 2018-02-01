package sokoban;

import javax.swing.*;
import java.awt.*;

public class Player {
	private Game game;
	private boolean toDisplay, isSol;
	private int x, y;
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;

	public Player(int x, int y, Game game, boolean toDisplay){
		this.x = x;
		this.y = y;
		this.game = game;
		this.toDisplay = toDisplay;
		this.isSol = false;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void solution(){
		this.isSol = true;
		this.toDisplay = false;
	}

	public void move(int direction){
		if(direction == UP){
			if(this.y > 0){
				if(this.game.getItem(this.x, this.y - 1).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, UP, "k", "e");
						this.y--;
						this.game.addMove(UP);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, UP, "k", "s");
						this.y--;
						this.game.addMove(UP);
					}
				} else if(this.game.getItem(this.x, this.y - 1).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, UP, "K", "e");
						this.y--;
						this.game.addMove(UP);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, UP, "K", "s");
						this.y--;
						this.game.addMove(UP);
					}
				} else if(this.game.getItem(this.x, this.y - 1).equals("w")){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.x, this.y - 1).equals("b") || this.game.getItem(this.x, this.y - 1).equals("B")){
					if(this.game.getItem(this.x, this.y - 2).equals("b") || this.game.getItem(this.x, this.y - 2).equals("B")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.x, this.y - 2).equals("w")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
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
						this.y--;
						this.game.addMove(UP);
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
						this.y--;
						this.game.addMove(UP);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.x, this.y, UP);
			}
		} else if(direction == LEFT){
			if(this.x > 0){
				if(this.game.getItem(this.x - 1, this.y).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, LEFT, "k", "e");
						this.x--;
						this.game.addMove(LEFT);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, LEFT, "k", "s");
						this.x--;
						this.game.addMove(LEFT);
					}
				} else if(this.game.getItem(this.x - 1, this.y).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, LEFT, "K", "e");
						this.x--;
						this.game.addMove(LEFT);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, LEFT, "K", "s");
						this.x--;
						this.game.addMove(LEFT);
					}
				} else if(this.game.getItem(this.x - 1, this.y).equals("w")){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.x - 1, this.y).equals("b") || this.game.getItem(this.x - 1, this.y).equals("B")){
					if(this.game.getItem(this.x - 2, this.y).equals("b") || this.game.getItem(this.x - 2, this.y).equals("B")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.x - 2, this.y).equals("w")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
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
						this.x--;
						this.game.addMove(LEFT);
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
						this.x--;
						this.game.addMove(LEFT);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.x, this.y, LEFT);
			}
		} else if(direction == DOWN){
			if(this.y < 10){
				if(this.game.getItem(this.x, this.y + 1).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, DOWN, "k", "e");
						this.y++;
						this.game.addMove(DOWN);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, DOWN, "k", "s");
						this.y++;
						this.game.addMove(DOWN);
					}
				} else if(this.game.getItem(this.x, this.y + 1).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, DOWN, "K", "e");
						this.y++;
						this.game.addMove(DOWN);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, DOWN, "K", "s");
						this.y++;
						this.game.addMove(DOWN);
					}
				} else if(this.game.getItem(this.x, this.y + 1).equals("w")){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.x, this.y + 1).equals("b") || this.game.getItem(this.x, this.y + 1).equals("B")){
					if(this.game.getItem(this.x, this.y + 2).equals("b") || this.game.getItem(this.x, this.y + 2).equals("B")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.x, this.y + 2).equals("w")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
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
						this.y++;
						this.game.addMove(DOWN);
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
						this.y++;
						this.game.addMove(DOWN);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.x, this.y, DOWN);
			}
		} else if(direction == RIGHT){
			if(this.x < 10){
				if(this.game.getItem(this.x + 1, this.y).equals("e")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, RIGHT, "k", "e");
						this.x++;
						this.game.addMove(RIGHT);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, RIGHT, "k", "s");
						this.x++;
						this.game.addMove(RIGHT);
					}
				} else if(this.game.getItem(this.x + 1, this.y).equals("s")){
					if(this.game.getItem(this.x, this.y).equals("k")){
						this.game.moveItem(this.x, this.y, RIGHT, "K", "e");
						this.x++;
						this.game.addMove(RIGHT);
					} else if(this.game.getItem(this.x, this.y).equals("K")){
						this.game.moveItem(this.x, this.y, RIGHT, "K", "s");
						this.x++;
						this.game.addMove(RIGHT);
					}
				} else if(this.game.getItem(this.x + 1, this.y).equals("w")){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.x + 1, this.y).equals("b") || this.game.getItem(this.x + 1, this.y).equals("B")){
					if(this.game.getItem(this.x + 2, this.y).equals("b") || this.game.getItem(this.x + 2, this.y).equals("B")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.x + 2, this.y).equals("w")){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
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
						this.x++;
						this.game.addMove(RIGHT);
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
						this.x++;
						this.game.addMove(RIGHT);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.x, this.y, RIGHT);
			}
		}
		if(this.toDisplay){
			this.game.checkWin();
			this.game.printMap();
		}
	}

	public boolean isValidMove(int direction){
		if(direction == UP){
			if(this.y > 0){
				if(this.game.getItem(this.x, this.y - 1).equals("w")){
					return false;
				} else if(this.game.getItem(this.x, this.y - 1).equals("b") || this.game.getItem(this.x, this.y - 1).equals("B")){
					if(this.game.getItem(this.x, this.y - 2).equals("b") || this.game.getItem(this.x, this.y - 2).equals("B")){
						return false;
					} else if(this.game.getItem(this.x, this.y - 2).equals("w")){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == LEFT){
			if(this.x > 0){
				if(this.game.getItem(this.x - 1, this.y).equals("w")){
					return false;
				} else if(this.game.getItem(this.x - 1, this.y).equals("b") || this.game.getItem(this.x - 1, this.y).equals("B")){
					if(this.game.getItem(this.x - 2, this.y).equals("b") || this.game.getItem(this.x - 2, this.y).equals("B")){
						return false;
					} else if(this.game.getItem(this.x - 2, this.y).equals("w")){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == DOWN){
			if(this.y < 10){
				if(this.game.getItem(this.x, this.y + 1).equals("w")){
					return false;
				} else if(this.game.getItem(this.x, this.y + 1).equals("b") || this.game.getItem(this.x, this.y + 1).equals("B")){
					if(this.game.getItem(this.x, this.y + 2).equals("b") || this.game.getItem(this.x, this.y + 2).equals("B")){
						return false;
					} else if(this.game.getItem(this.x, this.y + 2).equals("w")){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == RIGHT){
			if(this.x < 10){
				if(this.game.getItem(this.x + 1, this.y).equals("w")){
					return false;
				} else if(this.game.getItem(this.x + 1, this.y).equals("b") || this.game.getItem(this.x + 1, this.y).equals("B")){
					if(this.game.getItem(this.x + 2, this.y).equals("b") || this.game.getItem(this.x + 2, this.y).equals("B")){
						return false;
					} else if(this.game.getItem(this.x + 2, this.y).equals("w")){
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}
}