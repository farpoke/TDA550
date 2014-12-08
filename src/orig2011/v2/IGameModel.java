package orig2011.v2;

import java.awt.Dimension;

/**
 * Common superclass for all game model classes.
 * 
 * Constructors of subclasses should initiate matrix elements and additional,
 * game-dependent fields.
 */
public interface IGameModel {
	
	/**
	 * Returns the size of the gameboard.
	 */
	public Dimension getGameboardSize();
	
	/**
	 * Returns the GameTile in logical position (x,y) of the gameboard.
	 * 
	 * @param pos
	 *            The position in the gameboard matrix.
	 */
	public GameTile getGameboardState(final int x, final int y);
	
	/**
	 * This method is called repeatedly so that the game can update it's state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	public abstract void gameUpdate(int lastKey) throws GameOverException;
	
}
