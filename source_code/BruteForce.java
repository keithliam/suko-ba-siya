package sokoban;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Stack;

public class BruteForce {
	private Game initial;
	private Queue<Game> allStates;
	private Queue<Game> visitedStates;
	private Queue<Game> toExplore;

	// public boolean isVisited(String[][] map){}
	public BruteForce(Game game){
		this.initial = game;
		// this.toExplore.add(game);
		// printMap(game.getResult(2));
	}

	private Queue<Integer> Actions(Game game){
		return game.getValidMoves();
	}

	private Game Result(Game game, int direction){
		return game.getResult(direction);
	}

	private boolean GoalTest(Game game){
		return game.isFinished();
	}

	private int PathCost(Queue<Integer> path){
		Game game = new Game(null, null, 0, 0, 0, null);
		return game.getCost(path);
	}

	private boolean isExplored(Game game, Queue<Game> explored, Queue<Game> frontier){
		Game checkGame1, checkGame2;
		int size = explored.size();
		int size1 = frontier.size();
		boolean isEqual;
		for(int i = 0; i < size || i < size1; i++){
			if(i < size){
				checkGame1 = explored.remove();
			} else {
				checkGame1 = null;
			}
			if(i < size1){
				checkGame2 = frontier.remove();
			} else {
				checkGame2 = null;
			}
			if((checkGame1 != null && game.mapID.equals(checkGame1.mapID)) || (checkGame2 != null && game.mapID.equals(checkGame2.mapID))){
				return true;
			}
		}
		return false;
	}

	public Game breadthFirstSearch(){
		Queue<Game> frontier = new LinkedList<Game>();
		Queue<Game> explored = new LinkedList<Game>();
		Queue<Integer> actions;
		Game currentState, result;
		int action, size;
		boolean is1, is2;
		frontier.add(this.initial);
		while(frontier.size() != 0){
			currentState = frontier.remove();						// 6687 nanoseconds
																									// long timeA = System.nanoTime();
																									// long timeB = System.nanoTime();
																									// System.out.println("Elapsed time: " + (timeB - timeA) + " nanoseconds.");								
																									// return null;
			explored.add(currentState);								// 7267 nanoseconds
			// System.out.println(this.GoalTest(currentState));
			if(this.GoalTest(currentState)){
				// this.printMap(currentState);
				currentState.printMap();
				// System.out.println(currentState.getPrevMoves());
				return currentState;
			} else {
																									// long timeA = System.nanoTime();
				actions = this.Actions(currentState);				// 44965 nanoseconds
																									// long timeB = System.nanoTime();
																									// System.out.println("Elapsed time: " + (timeB - timeA) + " nanoseconds.");								
																									// return null;
				size = actions.size();								// 813 nanoseconds
				for(int i = 0; i < size; i++){
					action = actions.remove();						// 3425 nanoseconds
					result = this.Result(currentState, action);		// 5468 nanoseconds
					if(!this.isExplored(result, new LinkedList<Game>(explored), new LinkedList<Game>(frontier))){	// 18292 nanoseconds // 21209 nanoseconds
						// System.out.println("\n\nFrontier size: " + frontier.size() + "\nExplored size: " + explored.size());	// 38050 nanoseconds
						// System.out.println(result.getPrevMoves());	// 23360 nanoseconds
						frontier.add(result);						// 2585 nanoseconds
					}
				}
			}
		}
		return null;
	}

	private void printPlayerPosition(Game game){
		System.out.println("\nPlayer Position: (" + game.getPlayerX() + ", " + game.getPlayerY() + ")\n");
	}
}