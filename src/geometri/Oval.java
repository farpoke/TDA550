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
		place(x, y); // IllegalPositionException on negative coordinates.
		this.width = width;
		this.height = height;
		this.color = c;
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
		x = f.getX();
		y = f.getY();
		this.width = width;
		this.height = height;
		this.color = c;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return (int) Math.round(Math.PI * width * height / 4.0);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int compareTo(GeometricalForm f) {
		int areaDiff = getArea() - f.getArea();
		if (areaDiff != 0)
			return areaDiff;
		else
			return getPerimeter() - f.getPerimeter();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void move(int dx, int dy) throws IllegalPositionException {
		place(x + dx, y + dy);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getPerimeter() {
		// Semi-major axis, named a by convention.
		double a = Math.max(width, height) / 2.0;
		// Semi-minor axis, named b by convention.
		double b = Math.min(width, height) / 2.0;
		// Compute approximate perimeter using formula by Ramanujan
		double h = (a - b) * (a - b) / ((a + b) * (a + b));
		double c = Math.PI * (a + b) * (1 - 3 * h / (10 + Math.sqrt(4 - 3*h)));
		return (int)Math.round(c);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void place(int x, int y) throws IllegalPositionException {
		if (x < 0 || y < 0)
			throw new IllegalPositionException(String.format(
					"Illegal position (%d; %d)", x, y));
		this.x = x;
		this.y = y;
	}

}
