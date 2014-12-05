package geometri;

import java.awt.Color;

/**
 * Implementation of GeometricalForm that describes a circle.
 */
public class Circle extends Oval {

	/**
	 * Construct a circle with the given position, size and color.
	 * 
	 * @param x
	 *            The x-position, defined as the left side coordinate.
	 * @param y
	 *            The y-position, defined as the upper side coordinate.
	 * @param diameter
	 *            The diameter of the circle.
	 * @param c
	 *            The color of the circle.
	 * 
	 * @throws IllegalPositionException
	 *             Thrown if the x or y position is negative.
	 */
	public Circle(int x, int y, int diameter, Color c)
			throws IllegalPositionException {
		super(x, y, diameter, diameter, c);
	}

	/**
	 * Construct a circle with the same position as the given GeometricalForm,
	 * and the specified diameter and color.
	 * 
	 * @param f
	 *            A GeometricalForm instance which position will be used.
	 * @param diameter
	 *            The diameter of the circle.
	 * @param c
	 *            The color of the circle.
	 */
	public Circle(GeometricalForm f, int diameter, Color c) {
		super(f, diameter, diameter, c);
	}

}
