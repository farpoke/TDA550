package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a circle.
 */
public class Circle implements GeometricalForm {

	private int x, y;
	private int diameter;
	private Color color;

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
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int compareTo(GeometricalForm f) {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Color getColor() {
		return null;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getHeight() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getX() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getY() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void move(int dx, int dy) throws IllegalPositionException {
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getPerimeter() {
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void place(int x, int y) throws IllegalPositionException {
	}

}
