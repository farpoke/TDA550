package geometri;

import java.awt.Color;

/**
 * Implementation of GeometricalForm that describes a square.
 */
public class Square extends Rectangle {
	
	/**
	 * Construct a square with the given position, side length and color.
	 * 
	 * @param x
	 *            The x-coordinate of the position.
	 * @param y
	 *            The y-coordinate of the position.
	 * @param side
	 *            The side length of the square.
	 * @param c
	 *            The color of the square.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Square(int x, int y, int side, Color c)
			throws IllegalPositionException {
		super(x, y, side, side, c);
	}
	
	/**
	 * Construct a square with the same position as a given GeometricalForm, and
	 * the given side length and color.
	 * 
	 * @param f
	 *            The GeometricalForm which position to use.
	 * @param side
	 *            The side length of the square.
	 * @param c
	 *            The color of the square.
	 */
	public Square(GeometricalForm f, int side, Color c) {
		super(f, side, side, c);
	}
	
}
