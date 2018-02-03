package sokoban;

import javax.swing.*;
import java.awt.*;

public class Player {
	private Game game;
	private boolean toDisplay, isSol;
	private int playerX, playerY;
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;
	public final static int w = 1;
	public final static int e = 2;
	public final static int x = 3;
	public final static int b = 4;
	public final static int B = 5;
	public final static int k = 6;
	public final static int K = 7;
	public final static int s = 8;

	public Player(int playerX, int playerY, Game game, boolean toDisplay){
		this.playerX = playerX;
		this.playerY = playerY;
		this.game = game;
		this.toDisplay = toDisplay;
		this.isSol = false;
	}

	public int getX(){
		return this.playerX;
	}

	public int getY(){
		return this.playerY;
	}

	public void setXY(int playerX, int playerY){
		this.playerX = playerX;
		this.playerY = playerY;
	}

	public void solution(){
		this.isSol = true;
		this.toDisplay = false;
	}

	public void move(int direction){
		if(direction == UP){
			if(this.playerY > 0){
				if(this.game.getItem(this.playerX, this.playerY - 1) == e){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, UP, k, e);
						this.playerY--;
						this.game.addMove(UP);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, UP, k, s);
						this.playerY--;
						this.game.addMove(UP);
					}
				} else if(this.game.getItem(this.playerX, this.playerY - 1) == s){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, UP, K, e);
						this.playerY--;
						this.game.addMove(UP);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, UP, K, s);
						this.playerY--;
						this.game.addMove(UP);
					}
				} else if(this.game.getItem(this.playerX, this.playerY - 1) == w){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.playerX, this.playerY - 1) == b || this.game.getItem(this.playerX, this.playerY - 1) == B){
					if(this.game.getItem(this.playerX, this.playerY - 2) == b || this.game.getItem(this.playerX, this.playerY - 2) == B){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.playerX, this.playerY - 2) == w){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
					} else if(this.game.getItem(this.playerX, this.playerY - 2) == s){
						if(this.game.getItem(this.playerX, this.playerY - 1) == B){
							this.game.moveItem(this.playerX, this.playerY - 1, UP, B, s);
						} else if(this.game.getItem(this.playerX, this.playerY - 1) == b){
							this.game.moveItem(this.playerX, this.playerY - 1, UP, B, e);
							this.game.decStorage();
						}

						if(this.game.getItem(this.playerX, this.playerY - 1) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, UP, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, UP, k, s);
							}
						} else if(this.game.getItem(this.playerX, this.playerY - 1) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, UP, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, UP, K, s);
							}
						}
						this.playerY--;
						this.game.addMove(UP);
					} else if(this.game.getItem(this.playerX, this.playerY - 2) == e){
						if(this.game.getItem(this.playerX, this.playerY - 1) == B){
							this.game.moveItem(this.playerX, this.playerY - 1, UP, b, s);
							this.game.incStorage();
						} else if(this.game.getItem(this.playerX, this.playerY - 1) == b){
							this.game.moveItem(this.playerX, this.playerY - 1, UP, b, e);
						}

						if(this.game.getItem(this.playerX, this.playerY - 1) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, UP, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, UP, k, s);
							}
						} else if(this.game.getItem(this.playerX, this.playerY - 1) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, UP, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, UP, K, s);
							}
						}
						this.playerY--;
						this.game.addMove(UP);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.playerX, this.playerY, UP);
			}
		} else if(direction == LEFT){
			if(this.playerX > 0){
				if(this.game.getItem(this.playerX - 1, this.playerY) == e){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, LEFT, k, e);
						this.playerX--;
						this.game.addMove(LEFT);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, LEFT, k, s);
						this.playerX--;
						this.game.addMove(LEFT);
					}
				} else if(this.game.getItem(this.playerX - 1, this.playerY) == s){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, LEFT, K, e);
						this.playerX--;
						this.game.addMove(LEFT);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, LEFT, K, s);
						this.playerX--;
						this.game.addMove(LEFT);
					}
				} else if(this.game.getItem(this.playerX - 1, this.playerY) == w){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.playerX - 1, this.playerY) == b || this.game.getItem(this.playerX - 1, this.playerY) == B){
					if(this.game.getItem(this.playerX - 2, this.playerY) == b || this.game.getItem(this.playerX - 2, this.playerY) == B){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.playerX - 2, this.playerY) == w){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
					} else if(this.game.getItem(this.playerX - 2, this.playerY) == s){
						if(this.game.getItem(this.playerX - 1, this.playerY) == B){
							this.game.moveItem(this.playerX - 1, this.playerY, LEFT, B, s);
						} else if(this.game.getItem(this.playerX - 1, this.playerY) == b){
							this.game.moveItem(this.playerX - 1, this.playerY, LEFT, B, e);
							this.game.decStorage();
						}

						if(this.game.getItem(this.playerX - 1, this.playerY) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, LEFT, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, LEFT, k, s);
							}
						} else if(this.game.getItem(this.playerX - 1, this.playerY) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, LEFT, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, LEFT, K, s);
							}
						}
						this.playerX--;
						this.game.addMove(LEFT);
					} else if(this.game.getItem(this.playerX - 2, this.playerY) == e){
						if(this.game.getItem(this.playerX - 1, this.playerY) == B){
							this.game.moveItem(this.playerX - 1, this.playerY, LEFT, b, s);
							this.game.incStorage();
						} else if(this.game.getItem(this.playerX - 1, this.playerY) == b){
							this.game.moveItem(this.playerX - 1, this.playerY, LEFT, b, e);
						}

						if(this.game.getItem(this.playerX - 1, this.playerY) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, LEFT, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, LEFT, k, s);
							}
						} else if(this.game.getItem(this.playerX - 1, this.playerY) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, LEFT, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, LEFT, K, s);
							}
						}
						this.playerX--;
						this.game.addMove(LEFT);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.playerX, this.playerY, LEFT);
			}
		} else if(direction == DOWN){
			if(this.playerY < 10){
				if(this.game.getItem(this.playerX, this.playerY + 1) == e){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, DOWN, k, e);
						this.playerY++;
						this.game.addMove(DOWN);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, DOWN, k, s);
						this.playerY++;
						this.game.addMove(DOWN);
					}
				} else if(this.game.getItem(this.playerX, this.playerY + 1) == s){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, DOWN, K, e);
						this.playerY++;
						this.game.addMove(DOWN);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, DOWN, K, s);
						this.playerY++;
						this.game.addMove(DOWN);
					}
				} else if(this.game.getItem(this.playerX, this.playerY + 1) == w){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.playerX, this.playerY + 1) == b || this.game.getItem(this.playerX, this.playerY + 1) == B){
					if(this.game.getItem(this.playerX, this.playerY + 2) == b || this.game.getItem(this.playerX, this.playerY + 2) == B){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.playerX, this.playerY + 2) == w){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
					} else if(this.game.getItem(this.playerX, this.playerY + 2) == s){
						if(this.game.getItem(this.playerX, this.playerY + 1) == B){
							this.game.moveItem(this.playerX, this.playerY + 1, DOWN, B, s);
						} else if(this.game.getItem(this.playerX, this.playerY + 1) == b){
							this.game.moveItem(this.playerX, this.playerY + 1, DOWN, B, e);
							this.game.decStorage();
						}

						if(this.game.getItem(this.playerX, this.playerY + 1) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, DOWN, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, DOWN, k, s);
							}
						} else if(this.game.getItem(this.playerX, this.playerY + 1) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, DOWN, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, DOWN, K, s);
							}
						}
						this.playerY++;
						this.game.addMove(DOWN);
					} else if(this.game.getItem(this.playerX, this.playerY + 2) == e){
						if(this.game.getItem(this.playerX, this.playerY + 1) == B){
							this.game.moveItem(this.playerX, this.playerY + 1, DOWN, b, s);
							this.game.incStorage();
						} else if(this.game.getItem(this.playerX, this.playerY + 1) == b){
							this.game.moveItem(this.playerX, this.playerY + 1, DOWN, b, e);
						}

						if(this.game.getItem(this.playerX, this.playerY + 1) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, DOWN, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, DOWN, k, s);
							}
						} else if(this.game.getItem(this.playerX, this.playerY + 1) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, DOWN, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, DOWN, K, s);
							}
						}
						this.playerY++;
						this.game.addMove(DOWN);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.playerX, this.playerY, DOWN);
			}
		} else if(direction == RIGHT){
			if(this.playerX < 10){
				if(this.game.getItem(this.playerX + 1, this.playerY) == e){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, RIGHT, k, e);
						this.playerX++;
						this.game.addMove(RIGHT);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, RIGHT, k, s);
						this.playerX++;
						this.game.addMove(RIGHT);
					}
				} else if(this.game.getItem(this.playerX + 1, this.playerY) == s){
					if(this.game.getItem(this.playerX, this.playerY) == k){
						this.game.moveItem(this.playerX, this.playerY, RIGHT, K, e);
						this.playerX++;
						this.game.addMove(RIGHT);
					} else if(this.game.getItem(this.playerX, this.playerY) == K){
						this.game.moveItem(this.playerX, this.playerY, RIGHT, K, s);
						this.playerX++;
						this.game.addMove(RIGHT);
					}
				} else if(this.game.getItem(this.playerX + 1, this.playerY) == w){
					if(this.toDisplay){
						System.out.println("You're being blocked by a wall.");
					}
				} else if(this.game.getItem(this.playerX + 1, this.playerY) == b || this.game.getItem(this.playerX + 1, this.playerY) == B){
					if(this.game.getItem(this.playerX + 2, this.playerY) == b || this.game.getItem(this.playerX + 2, this.playerY) == B){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by another blue book.");
						}
					} else if(this.game.getItem(this.playerX + 2, this.playerY) == w){
						if(this.toDisplay){
							System.out.println("Blue book is being blocked by a wall.");
						}
					} else if(this.game.getItem(this.playerX + 2, this.playerY) == s){
						if(this.game.getItem(this.playerX + 1, this.playerY) == B){
							this.game.moveItem(this.playerX + 1, this.playerY, RIGHT, B, s);
						} else if(this.game.getItem(this.playerX + 1, this.playerY) == b){
							this.game.moveItem(this.playerX + 1, this.playerY, RIGHT, B, e);
							this.game.decStorage();
						}

						if(this.game.getItem(this.playerX + 1, this.playerY) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, k, s);
							}
						} else if(this.game.getItem(this.playerX + 1, this.playerY) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, K, s);
							}
						}
						this.playerX++;
						this.game.addMove(RIGHT);
					} else if(this.game.getItem(this.playerX + 2, this.playerY) == e){
						if(this.game.getItem(this.playerX + 1, this.playerY) == B){
							this.game.moveItem(this.playerX + 1, this.playerY, RIGHT, b, s);
							this.game.incStorage();
						} else if(this.game.getItem(this.playerX + 1, this.playerY) == b){
							this.game.moveItem(this.playerX + 1, this.playerY, RIGHT, b, e);
						}

						if(this.game.getItem(this.playerX + 1, this.playerY) == e){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, k, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, k, s);
							}
						} else if(this.game.getItem(this.playerX + 1, this.playerY) == s){
							if(this.game.getItem(this.playerX, this.playerY) == k){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, K, e);
							} else if(this.game.getItem(this.playerX, this.playerY) == K){
								this.game.moveItem(this.playerX, this.playerY, RIGHT, K, s);
							}
						}
						this.playerX++;
						this.game.addMove(RIGHT);
					}
				}
			}
			if(this.toDisplay || this.isSol){
				this.game.renderTiles(this.playerX, this.playerY, RIGHT);
			}
		}
		if(this.toDisplay){
			this.game.checkWin();
			this.game.printMap();
		}
	}

	public boolean isValidMove(int direction){
		if(direction == UP){
			if(this.playerY > 0){
				if(this.game.getItem(this.playerX, this.playerY - 1) == w){
					return false;
				} else if(this.game.getItem(this.playerX, this.playerY - 1) == b || this.game.getItem(this.playerX, this.playerY - 1) == B){
					if(this.game.getItem(this.playerX, this.playerY - 2) == b || this.game.getItem(this.playerX, this.playerY - 2) == B){
						return false;
					} else if(this.game.getItem(this.playerX, this.playerY - 2) == w){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == LEFT){
			if(this.playerX > 0){
				if(this.game.getItem(this.playerX - 1, this.playerY) == w){
					return false;
				} else if(this.game.getItem(this.playerX - 1, this.playerY) == b || this.game.getItem(this.playerX - 1, this.playerY) == B){
					if(this.game.getItem(this.playerX - 2, this.playerY) == b || this.game.getItem(this.playerX - 2, this.playerY) == B){
						return false;
					} else if(this.game.getItem(this.playerX - 2, this.playerY) == w){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == DOWN){
			if(this.playerY < 10){
				if(this.game.getItem(this.playerX, this.playerY + 1) == w){
					return false;
				} else if(this.game.getItem(this.playerX, this.playerY + 1) == b || this.game.getItem(this.playerX, this.playerY + 1) == B){
					if(this.game.getItem(this.playerX, this.playerY + 2) == b || this.game.getItem(this.playerX, this.playerY + 2) == B){
						return false;
					} else if(this.game.getItem(this.playerX, this.playerY + 2) == w){
						return false;
					}
				}
			} else {
				return false;
			}
		} else if(direction == RIGHT){
			if(this.playerX < 10){
				if(this.game.getItem(this.playerX + 1, this.playerY) == w){
					return false;
				} else if(this.game.getItem(this.playerX + 1, this.playerY) == b || this.game.getItem(this.playerX + 1, this.playerY) == B){
					if(this.game.getItem(this.playerX + 2, this.playerY) == b || this.game.getItem(this.playerX + 2, this.playerY) == B){
						return false;
					} else if(this.game.getItem(this.playerX + 2, this.playerY) == w){
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