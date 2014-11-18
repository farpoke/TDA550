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
	 * @param x1 The x-coordinate of the first position.
	 * @param y1 The y-coordinate of the first position.
	 * @param x2 The x-coordinate of the second position.
	 * @param y2 The y-coordinate of the second position.
	 * @param c The color of the line.
	 * @throws IllegalPositionException
	 * Thrown if any of the coordinates are negative.
	 */
	public Line(int x1, int y1, int x2, int y2, Color c)
			throws IllegalPositionException {
	}

	/**
	 * Construct a line between the positions of two GeometricalForms, with the given color.
	 * 
	 * @param f1 The first geometrical form.
	 * @param f2 The second geometrical form.
	 * @param c The color of the line.
	 */
	public Line(GeometricalForm f1, GeometricalForm f2, Color c) {
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
	 * Returns the perimeter of the line, which is defined as its length.
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
