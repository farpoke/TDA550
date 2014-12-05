package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a rectangle.
 */
public class Rectangle extends AbstractForm {

	/**
	 * Construct a rectangle with the given position, width, height and color.
	 * 
	 * @param x
	 *            The x-coordinate of the position.
	 * @param y
	 *            The y-coordinate of the position.
	 * @param width
	 *            The width of the rectangle.
	 * @param height
	 *            The height of the rectangle.
	 * @param c
	 *            The color of the rectangle.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Rectangle(int x, int y, int width, int height, Color c)
			throws IllegalPositionException {
		super(x, y, width, height, c);
	}

	/**
	 * Construct a rectangle with the position of the given GeometricalForm, and
	 * the given width, height and color.
	 * 
	 * @param f
	 *            The GeometricalForm which position to use.
	 * @param width
	 *            The width of the rectangle.
	 * @param height
	 *            The height of the rectangle.
	 * @param c
	 *            The color of the rectangle.
	 */
	public Rectangle(GeometricalForm f, int width, int height, Color c) {
		super(f, width, height, c);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return getWidth() * getHeight();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getPerimeter() {
		return 2 * (getWidth() + getHeight());
	}
	
}
