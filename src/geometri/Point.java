package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a point.
 */
public class Point implements GeometricalForm {

	private int x, y;
	private Color color;

	/**
	 * Construct a point with the given position and color.
	 * 
	 * @param x
	 *            The x-coordinate of the position.
	 * @param y
	 *            The y-coordinate of the position.
	 * @param c
	 *            The color of the point.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Point(int x, int y, Color c) throws IllegalPositionException {
	}

	/**
	 * Construct a point with the same position as the given GeometricalForm,
	 * and the given color.
	 * 
	 * @param f
	 *            The GeometricalForm which position to use.
	 * @param c
	 *            The color of the point.
	 */
	public Point(GeometricalForm f, Color c) {
	}

	/**
	 * The area of a point is defined as zero.
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
	 * The width of a point is defined as zero.
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/**
	 * The height of a point is defined as zero.
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
	 * The perimeter of a point is defined as zero.
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
