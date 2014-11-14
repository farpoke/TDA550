package lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SnakeModel extends GameModel {
	public enum Directions {
		EAST(1, 0), WEST(-1, 0), NORTH(0, -1), SOUTH(0, 1), NONE(0, 0);
		private final int xDelta;
		private final int yDelta;
		
		Directions(final int xDelta, final int yDelta) {
			this.xDelta = xDelta;
			this.yDelta = yDelta;
		}
		
		public int getXDelta() {
			return this.xDelta;
		}
		
		public int getYDelta() {
			return this.yDelta;
		}
	}
	
	private static final int COIN_START_AMOUNT = 20;
	/*
	 * The following GameTile objects are used only to describe how to paint the
	 * specified item.
	 * 
	 * This means that they should only be used in conjunction with the
	 * get/setGameboardState() methods.
	 */
	/** Graphical representation of a coin. */
	private static final GameTile COIN_TILE = new RoundTile(new Color(255, 215,
			0), new Color(255, 255, 0), 2.0);
	/** Graphical representation of the collector */
	private static final GameTile COLLECTOR_TILE = new RoundTile(Color.BLACK,
			Color.RED, 2.0);
	/** Graphical representation of the tail */
	private static final GameTile TAIL_TILE = new RoundTile(Color.BLACK,
			Color.BLACK, 2.0);
	/** Graphical representation of a blank tile. */
	private static final GameTile BLANK_TILE = new GameTile();
	/** A list containing the positions of all coins. */
	private final List<Position> coins = new ArrayList<Position>();
	/*
	 * The declaration and object creation above uses the new language feature
	 * 'generic types'. It can be declared in the old way like this: private
	 * java.util.List coins = new ArrayList(); This will however result in a
	 * warning at compilation "Position" in this case is the type of the objects
	 * that are going to be used in the List
	 */
	/** The number of coins found. */
	private int score;
	/** The positions of the snake. Object 0 indicates the collector */
	private ArrayList<Position> snakePos = new ArrayList<Position>();
	/** The direction of the collector. */
	private Directions direction = Directions.NORTH;
	private boolean toAddTail = false;
	
	/**
	 * Create a new model for the snake game.
	 */
	public SnakeModel() {
		Dimension size = getGameboardSize();
		// Blank out the whole gameboard
		for (int i = 0; i < size.width; i++) {
			for (int j = 0; j < size.height; j++) {
				setGameboardState(i, j, BLANK_TILE);
			}
		}
		// Insert the collector in the middle of the gameboard.
		snakePos.add(new Position(size.width / 2, size.height / 2));
		setGameboardState(snakePos.get(0), COLLECTOR_TILE);
		// Insert coins into the gameboard.
		for (int i = 0; i < COIN_START_AMOUNT; i++) {
			addCoin();
		}
	}
	
	/**
	 * Insert another coin into the gameboard.
	 */
	private void addCoin() {
		Position newCoinPos;
		Dimension size = getGameboardSize();
		// Loop until a blank position is found and ...
		do {
			newCoinPos = new Position((int) (Math.random() * size.width),
					(int) (Math.random() * size.height));
		} while (!isPositionEmpty(newCoinPos));
		// ... add a new coin to the empty tile.
		setGameboardState(newCoinPos, COIN_TILE);
		this.coins.add(newCoinPos);
	}
	
	/**
	 * Return whether the specified position is empty.
	 * 
	 * @param pos
	 *            The position to test.
	 * @return true if position is empty.
	 */
	private boolean isPositionEmpty(final Position pos) {
		return (getGameboardState(pos) == BLANK_TILE);
	}
	
	/**
	 * Update the direction of the collector according to the user's keypress.
	 */
	private void updateDirection(final int key) {
		switch (key) {
			case KeyEvent.VK_LEFT:
				direction = Directions.WEST;
				break;
			case KeyEvent.VK_UP:
				direction = Directions.NORTH;
				break;
			case KeyEvent.VK_RIGHT:
				direction = Directions.EAST;
				break;
			case KeyEvent.VK_DOWN:
				direction = Directions.SOUTH;
				break;
			default:
				// Don't change direction if another key is pressed
				break;
		}
	}
	
	/**
	 * Get next position of the collector.
	 */
	private Position getNextCollectorPos() {
		return new Position(snakePos.get(0).getX() + direction.getXDelta(),
				snakePos.get(0).getY() + direction.getYDelta());
	}
	
	private Position getLastPos() {
		return snakePos.get(snakePos.size() - 1);
	}
	
	private boolean crashed(ArrayList<Position> posArray) {
		for (int i = 0; i < posArray.size(); i++) {
			for (int j = i + 1; j < posArray.size(); j++) {
				if (posArray.get(i).getX() == posArray.get(j).getX()
						&& posArray.get(i).getY() == posArray.get(j).getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method is called repeatedly so that the game can update its state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	@Override
	public void gameUpdate(final int lastKey) throws GameOverException {
		setGameboardState(getLastPos(), BLANK_TILE);
		if (toAddTail) {
			snakePos.add(snakePos.get(snakePos.size() - 1));
			toAddTail = false;
		}
		// move and paint tail
		snakePos.add(1, snakePos.get(0));
		snakePos.remove(snakePos.size() - 1);
		for (int i = 1; i < snakePos.size(); i++) {
			setGameboardState(snakePos.get(i), TAIL_TILE);
		}
		updateDirection(lastKey);
		snakePos.set(0, getNextCollectorPos());
		if (isOutOfBounds(snakePos.get(0))) {
			System.out.println("Crashed into wall");
			throw new GameOverException(this.score);
		}
		setGameboardState(snakePos.get(0), COLLECTOR_TILE);
		// test for crashing into tail
		if (crashed(snakePos)) {
			System.out.println("Crashed into self");
			throw new GameOverException(this.score);
		}
		// Remove the coin at the new collector position (if any)
		// Coin collected
		if (this.coins.remove(snakePos.get(0))) {
			this.score++;
			toAddTail = true;
			// Coin not collected
		}
		// Check if all coins are found
		if (this.coins.isEmpty()) {
			throw new GameOverException(this.score + 5);
		}
		// Remove one of the coins
		Position oldCoinPos = this.coins.get(0);
		this.coins.remove(0);
		setGameboardState(oldCoinPos, BLANK_TILE);
		// Add a new coin (simulating moving one coin)
		addCoin();
	}
	
	/**
	 * 
	 * @param pos
	 *            The position to test.
	 * @return <code>false</code> if the position is outside the playing field,
	 *         <code>true</code> otherwise.
	 */
	private boolean isOutOfBounds(Position pos) {
		return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
				|| pos.getY() < 0 || pos.getY() >= getGameboardSize().height;
	}
}
