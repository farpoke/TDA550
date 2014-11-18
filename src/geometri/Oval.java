package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes an oval.
 */
public class Oval implements GeometricalForm {

	private int x, y;
	private int width, height;
	private Color color;

	/**
	 * Construct an oval with the given position, width, height and color.
	 * 
	 * @param x
	 *            The x-coordinate of the position.
	 * @param y
	 *            The y-coordinate of the position.
	 * @param width
	 *            The width of the oval.
	 * @param height
	 *            The height of the oval.
	 * @param c
	 *            The color of the oval.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Oval(int x, int y, int width, int height, Color c)
			throws IllegalPositionException {
	}

	/**
	 * Construct an oval with the position of the given GeometricalForm, and the
	 * given width, height and color.
	 * 
	 * @param f
	 *            The GeometricalForm which position to use.
	 * @param width
	 *            The width of the oval.
	 * @param height
	 *            The height of the oval.
	 * @param c
	 *            The color of the oval.
	 */
	public Oval(GeometricalForm f, int width, int height, Color c) {
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
