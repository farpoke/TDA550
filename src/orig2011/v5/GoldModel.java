package orig2011.v5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
public class GoldModel implements GameModel {
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
	private static final Dimension BOARD_SIZE = Constants.getGameSize();
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
	/** Graphical representation of a blank tile. */
	private static final GameTile BLANK_TILE = new BlankTile();
	/** A list containing the positions of all coins. */
	private final List<Position> coins = new ArrayList<Position>();
	
	private GameTile[][] board;
	
	/*
	 * The declaration and object creation above uses the new language feature
	 * 'generic types'. It can be declared in the old way like this: private
	 * java.util.List coins = new ArrayList(); This will however result in a
	 * warning at compilation "Position" in this case is the type of the objects
	 * that are going to be used in the List
	 */
	/** The position of the collector. */
	private Position collectorPos;
	/** The direction of the collector. */
	private Directions direction = Directions.NORTH;
	/** The number of coins found. */
	private int score;
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	/**
	 * Create a new model for the gold game.
	 */
	public GoldModel() {
		board = new GameTile[BOARD_SIZE.width][BOARD_SIZE.height];
		// Blank out the whole gameboard
		for (int i = 0; i < BOARD_SIZE.width; i++) {
			for (int j = 0; j < BOARD_SIZE.height; j++) {
				GameUtils.setGameboardState(board, i, j, BLANK_TILE);
			}
		}
		// Insert the collector in the middle of the gameboard.
		collectorPos = new Position(BOARD_SIZE.width / 2, BOARD_SIZE.height / 2);
		GameUtils.setGameboardState(board, collectorPos, COLLECTOR_TILE);
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
		// Loop until a blank position is found and ...
		do {
			newCoinPos = new Position((int) (Math.random() * BOARD_SIZE.width),
					(int) (Math.random() * BOARD_SIZE.height));
		} while (!isPositionEmpty(newCoinPos));
		// ... add a new coin to the empty tile.
		GameUtils.setGameboardState(board, newCoinPos, COIN_TILE);
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
		return GameUtils.getGameboardState(board, pos) == BLANK_TILE;
	}
	
	/**
	 * Update the direction of the collector according to the user's keypress.
	 */
	private void updateDirection(final int key) {
		switch (key) {
			case KeyEvent.VK_LEFT:
				this.direction = Directions.WEST;
				break;
			case KeyEvent.VK_UP:
				this.direction = Directions.NORTH;
				break;
			case KeyEvent.VK_RIGHT:
				this.direction = Directions.EAST;
				break;
			case KeyEvent.VK_DOWN:
				this.direction = Directions.SOUTH;
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
		return new Position(this.collectorPos.getX()
				+ this.direction.getXDelta(), this.collectorPos.getY()
				+ this.direction.getYDelta());
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
		GameUtils.setGameboardState(board, collectorPos, BLANK_TILE);
		// Change collector position.
		collectorPos = getNextCollectorPos();
		if (isOutOfBounds(collectorPos)) {
			throw new GameOverException(score);
		}
		// Draw collector at new position.
		GameUtils.setGameboardState(board, collectorPos, COLLECTOR_TILE);
		// Remove the coin at the new collector position (if any)
		if (coins.remove(collectorPos)) {
			score++;
		}
		// Check if all coins are found
		if (coins.isEmpty()) {
			throw new GameOverException(score + 5);
		}
		// Remove one of the coins
		Position oldCoinPos = coins.get(0);
		coins.remove(0);
		GameUtils.setGameboardState(board, oldCoinPos, BLANK_TILE);
		// Add a new coin (simulating moving one coin)
		addCoin();
		//
		propertyChangeSupport.firePropertyChange("GameBoardState", null, null);
	}
	
	/**
	 * 
	 * @param pos
	 *            The position to test.
	 * @return <code>false</code> if the position is outside the playing field,
	 *         <code>true</code> otherwise.
	 */
	private boolean isOutOfBounds(Position pos) {
		return pos.getX() < 0 || pos.getX() >= BOARD_SIZE.width
				|| pos.getY() < 0 || pos.getY() >= BOARD_SIZE.height;
	}

	@Override
	public GameTile getGameboardState(int x, int y) {
		return GameUtils.getGameboardState(board, x, y);
	}

	@Override
	public Dimension getGameboardSize() {
		return BOARD_SIZE;
	}

	@Override
	public void addObserver(PropertyChangeListener observer) {
		propertyChangeSupport.addPropertyChangeListener(observer);
	}

	@Override
	public void removeObserver(PropertyChangeListener observer) {
		propertyChangeSupport.removePropertyChangeListener(observer);
	}
}
