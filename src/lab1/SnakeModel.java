package lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Simple Snake game.
 * 
 * The player controls a snake and attempts to collect coins, causing the snake
 * to grow until it hits the wall or itself, or runs out of space.
 */
public class SnakeModel extends GameModel {

	/**
	 * Simple enumeration describing the possible directions the snake can
	 * travel in.
	 */
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

	/** The positions of the snake. Object 0 indicates the collector */
	private ArrayList<Position> snakePos = new ArrayList<Position>();
	/** The direction of the collector. */
	private Directions direction = Directions.NORTH;
	/** Flag indicating if a tail piece should be added on the next update. */
	private boolean toAddTail = false;
	/** The current position of the coin. */
	private Position cointPosition = null;
	/** The number of coins found. */
	private int score;

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
		// Place the coin on the gameboard.
		placeCoin();
	}

	/**
	 * Place the coin in an empty position on the gameboard.
	 * 
	 * @return true if the coin was placed or false if the board is full.
	 */
	private boolean placeCoin() {
		Position newCoinPos = null;
		Dimension size = getGameboardSize();
		// Iterate over the board and randomly select an empty spot.
		int emptyCount = 0;
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {
				// Only look at blank cell.
				if (getGameboardState(x, y) != BLANK_TILE)
					continue;
				// Randomly select blank cell.
				emptyCount++;
				if (newCoinPos == null || (Math.random() < 1.0 / emptyCount))
					newCoinPos = new Position(x, y);
			}
		}
		// Did we find an empty spot?
		if (newCoinPos == null) {
			return false; // No.
		} else {
			// Yes, place coin.
			setGameboardState(newCoinPos, COIN_TILE);
			cointPosition = newCoinPos;
			return true;
		}
	}

	/**
	 * Update the direction of the collector according to the user's keypress.
	 * Does not allow 180-degree turns.
	 */
	private void updateDirection(final int key) {
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (direction != Directions.EAST)
				direction = Directions.WEST;
			break;
		case KeyEvent.VK_UP:
			if (direction != Directions.SOUTH)
				direction = Directions.NORTH;
			break;
		case KeyEvent.VK_RIGHT:
			if (direction != Directions.WEST)
				direction = Directions.EAST;
			break;
		case KeyEvent.VK_DOWN:
			if (direction != Directions.NORTH)
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

	/**
	 * This method is called repeatedly so that the game can update its state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	@Override
	public void gameUpdate(final int lastKey) throws GameOverException {
		// Respond to key presses.
		updateDirection(lastKey);
		// Move snake forward one step.
		snakePos.add(1, snakePos.get(0));
		// Remove tail piece, unless we wish to keep it.
		if (!toAddTail) {
			int lastIndex = snakePos.size() - 1;
			setGameboardState(snakePos.get(lastIndex), BLANK_TILE);
			snakePos.remove(lastIndex);
		} else {
			toAddTail = false;
		}
		// Change collector position.
		snakePos.set(0, getNextCollectorPos());
		// Replace previous collector position with tail piece if appropriate.
		if (snakePos.size() > 1)
			setGameboardState(snakePos.get(1), TAIL_TILE);
		// Test for crashing into wall
		if (isOutOfBounds(snakePos.get(0))) {
			System.out.println("Crashed into wall");
			throw new GameOverException(this.score);
		}
		// Test for crashing into tail.
		if (getGameboardState(snakePos.get(0)) == TAIL_TILE) {
			System.out.println("Crashed into self");
			throw new GameOverException(this.score);
		}
		// Draw collector at new position.
		setGameboardState(snakePos.get(0), COLLECTOR_TILE);
		// Check if the coin was collected.
		if (snakePos.get(0).equals(cointPosition)) {
			this.score++;
			toAddTail = true;
			// Replace coin if possible, or end game.
			if (!placeCoin()) {
				System.out.println("You won!");
				throw new GameOverException(this.score);
			}
		}
	}

	/**
	 * Check if the specified position is out of bounds.
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
