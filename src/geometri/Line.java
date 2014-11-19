package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a line.
 */
public class Line implements GeometricalForm {

	private int x1, y1;
	private int x2, y2;
	private Color color;

	/**
	 * Construct a line between two given positions, with the given color.
	 * 
	 * @param x1
	 *            The x-coordinate of the first position.
	 * @param y1
	 *            The y-coordinate of the first position.
	 * @param x2
	 *            The x-coordinate of the second position.
	 * @param y2
	 *            The y-coordinate of the second position.
	 * @param c
	 *            The color of the line.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Line(int x1, int y1, int x2, int y2, Color c)
			throws IllegalPositionException {
		// Check first coordinate.
		if (x1 < 0 || y1 < 0)
			throw new IllegalPositionException(String.format(
					"Illegal position #1 in Line constructor (%d; %d)", x1, y1));
		// Check second coordinate.
		if (x2 < 0 || y2 < 0)
			throw new IllegalPositionException(String.format(
					"Illegal position #2 in Line constructor (%d; %d)", x2, y2));
		// Ok.
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = c;
	}

	/**
	 * Construct a line between the positions of two GeometricalForms, with the
	 * given color.
	 * 
	 * @param f1
	 *            The first geometrical form.
	 * @param f2
	 *            The second geometrical form.
	 * @param c
	 *            The color of the line.
	 */
	public Line(GeometricalForm f1, GeometricalForm f2, Color c) {
		// f1 and f2 have valid coordinates by contract.
		this.x1 = f1.getX();
		this.y1 = f1.getY();
		this.x2 = f2.getX();
		this.y2 = f2.getY();
		this.color = c;
	}

	/**
	 * The area of a line is defined as zero.
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
		if (f.getArea() > 0)
			return -1;
		else
			return getPerimeter() - f.getPerimeter();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
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
		return Math.abs(x1 - x2);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getHeight() {
		return Math.abs(y1 - y2);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getX() {
		return Math.min(x1, x2);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getY() {
		return Math.min(y1, y2);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void move(int dx, int dy) throws IllegalPositionException {
		place(getX() + dx, getY() + dy);
	}

	/**
	 * Returns the perimeter of the line, which is defined as its length.
	 */
	@Override
	public int getPerimeter() {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void place(int x, int y) throws IllegalPositionException {
		// Check that the given point is valid.
		if (x < 0 || y < 0)
			throw new IllegalPositionException(String.format(
					"Illegal position (%d; %d)", x, y));
		// Compute amount needed to move to place the upper left corner at the
		// given point.
		int moveX = x - getX();
		int moveY = y - getY();
		// Move endpoints.
		x1 += moveX;
		y1 += moveY;
		x2 += moveX;
		y2 += moveY;
	}

}
