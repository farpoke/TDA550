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
	/** The directions of the snake. Object 0 indicates the collector */
	private ArrayList<Directions> snakeDir = new ArrayList<Directions>();
	
	/**
	 * Create a new model for the snake game.
	 */
	public SnakeModel() {
		snakeDir.add(0, Directions.NORTH);
		Dimension size = getGameboardSize();
		// Blank out the whole gameboard
		for (int i = 0; i < size.width; i++) {
			for (int j = 0; j < size.height; j++) {
				setGameboardState(i, j, BLANK_TILE);
			}
		}
		// Insert the collector in the middle of the gameboard.
		snakePos.add(0, new Position(size.width / 2, size.height / 2));
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
				snakeDir.set(0, Directions.WEST);
				break;
			case KeyEvent.VK_UP:
				snakeDir.set(0, Directions.NORTH);
				break;
			case KeyEvent.VK_RIGHT:
				snakeDir.set(0, Directions.EAST);
				break;
			case KeyEvent.VK_DOWN:
				snakeDir.set(0, Directions.SOUTH);
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
		return new Position(snakePos.get(0).getX()
				+ snakeDir.get(0).getXDelta(), snakePos.get(0).getY()
				+ snakeDir.get(0).getYDelta());
	}
	
	private Position getLastPos() {
		return snakePos.get(snakePos.size() - 1);
	}
	
	private Directions getLastDir() {
		return snakeDir.get(snakeDir.size() - 1);
	}
	
	/**
	 * This method is called repeatedly so that the game can update its state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	@Override
	public void gameUpdate(final int lastKey) throws GameOverException {
		updateDirection(lastKey);
		// Erase the previous position.
		setGameboardState(snakePos.get(0), BLANK_TILE);
		// Change collector position.
		snakePos.set(0, getNextCollectorPos());
		if (isOutOfBounds(snakePos.get(0))) {
			throw new GameOverException(this.score);
		}
		// Draw collector at new position.
		setGameboardState(snakePos.get(0), COLLECTOR_TILE);
		for (int i = 1; i < snakePos.size(); i++) {
			setGameboardState(snakePos.get(i), TAIL_TILE);
		}
		// Remove the coin at the new collector position (if any)
		if (this.coins.remove(snakePos.get(0))) {
			this.score++;
			// TODO add tail at last tail position minus direction
			snakePos.add(new Position(getLastPos().getX()
					+ getLastDir().getXDelta(), getLastPos().getY()
					+ getLastDir().getYDelta()));
			snakeDir.add(getLastDir());
			setGameboardState(getLastPos(), TAIL_TILE);
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
