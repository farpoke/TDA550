package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes an oval.
 */
public class Oval extends AbstractForm {

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
		super(x, y, width, height, c);
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
		super(f, width, height, c);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return (int) Math.round(Math.PI * getWidth() * getHeight() / 4.0);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getPerimeter() {
		// Semi-major axis, named a by convention.
		double a = Math.max(getWidth(), getHeight()) / 2.0;
		// Semi-minor axis, named b by convention.
		double b = Math.min(getWidth(), getHeight()) / 2.0;
		// Compute approximate perimeter using formula by Ramanujan
		double h = (a - b) * (a - b) / ((a + b) * (a + b));
		double c = Math.PI * (a + b) * (1 - 3 * h / (10 + Math.sqrt(4 - 3*h)));
		return (int)Math.round(c);
	}

}
