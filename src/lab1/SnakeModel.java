package lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample game for illustration. Intentionally stupid; more interesting games to
 * be provided by students.
 * <p>
 * Initially 20 gold coins are randomly placed in the matrix. The red gold
 * collector aims to collect these coins which disappear after collection. Each
 * coin is randomly moved to a new position every n moves, where n is the number
 * of remaining coins. The game is won when all coins are collected and lost
 * when collector leaves game board.
 */
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

    /** The current position of the coin. */
    private Position cointPosition = null;

	/*
	 * The declaration and object creation above uses the new language feature
	 * 'generic types'. It can be declared in the old way like this: private
	 * java.util.List coins = new ArrayList(); This will however result in a
	 * warning at compilation "Position" in this case is the type of the objects
	 * that are going to be used in the List
	 */
	/** The positions of the snake. Object 0 indicates the collector */
	private ArrayList<Position> snakePos = new ArrayList<Position>();
	/** The direction of the collector. */
	private Directions direction = Directions.NORTH;
	/** The number of coins found. */
	private int score;
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
                Position currentPos = new Position(x, y);
                if (!isPositionEmpty(currentPos))
                    continue; // Ignore filled cells.
                emptyCount++;
                if (newCoinPos == null || (Math.random() < 1.0/emptyCount))
                    newCoinPos = currentPos;
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
	
	/**
	 * Get the last position in a position ListArray
	 */
	private Position getLastPos() {
		return snakePos.get(snakePos.size() - 1);
	}
	
	/**
	 * This method returns true when a collision is found and false when not.
	 * 
	 * @param posArray
	 *            ArrayList containing the Positions to look for collisions at.
	 */
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
        // Respond to key presses.
		updateDirection(lastKey);
        // Draw snake one step forward.
        setGameboardState(getLastPos(), BLANK_TILE);
        if (snakePos.size() > 1)
            setGameboardState(snakePos.get(0), TAIL_TILE);
        // Move snake forward one step.
		snakePos.add(1, snakePos.get(0));
		snakePos.remove(snakePos.size() - 1);
		// Change collector position.
		snakePos.set(0, getNextCollectorPos());
        // Draw collector at new position.
        setGameboardState(snakePos.get(0), COLLECTOR_TILE);
		// Test for crashing into wall
		if (isOutOfBounds(snakePos.get(0))) {
			System.out.println("Crashed into wall");
			throw new GameOverException(this.score);
		}
		// Test for crashing into tail.
		if (crashed(snakePos)) {
			System.out.println("Crashed into self");
			throw new GameOverException(this.score);
		}
		// Add tail if needed
		if (toAddTail) {
			snakePos.add(snakePos.get(snakePos.size() - 1));
			toAddTail = false;
		}
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
