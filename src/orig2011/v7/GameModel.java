package orig2011.v7;

import java.awt.Dimension;

/**
 * Common superclass for all game model classes.
 * 
 * Constructors of subclasses should initiate matrix elements and additional,
 * game-dependent fields.
 */
public interface GameModel extends IObservable {
	/**
	 * Returns the size of the gameboard.
	 */
	public Dimension getGameboardSize();
	
	/**
	 * Returns the GameTile in logical position (x,y) of the gameboard.
	 * 
	 * @param x
	 *            The x-position in the gameboard matrix.
	 * @param y
	 *            The y-position in the gameboard matrix.
	 */
	public GameTile getGameboardState(final int x, final int y);
	
	/**
	 * Returns the GameTile in logical position (x,y) of the gameboard.
	 * 
	 * @param pos
	 *            The position in the gameboard matrix.
	 */
	public GameTile getGameboardState(Position pos);
	
	/**
	 * This method is called repeatedly so that the game can update it's state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	public void gameUpdate(int lastKey) throws GameOverException;
	
	/**
	 * Return the desired delay between update calls, or a value less than zero
	 * if updates should be triggered by user input.
	 */
	public int getUpdateSpeed();
}
