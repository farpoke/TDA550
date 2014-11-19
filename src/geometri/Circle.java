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
		place(x, y); // Throws IllegalPositionException on negative coordinates.
		this.diameter = diameter;
		this.color = c;
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
		// f is guaranteed to have valid coordinates by contract.
		x = f.getX();
		y = f.getY();
		this.diameter = diameter;
		this.color = c;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return (int) Math.round(Math.PI * this.diameter * this.diameter / 4.0);
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
		g.fillOval(x, y, diameter, diameter);
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
		return diameter;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int getHeight() {
		return diameter;
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
		return (int) Math.round(Math.PI * diameter);
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
