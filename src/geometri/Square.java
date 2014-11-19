package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a square.
 */
public class Square implements GeometricalForm {
	private int x, y;
	private int side;
	private Color color;
	
	/**
	 * Construct a square with the given position, side length and color.
	 * 
	 * @param x
	 *            The x-coordinate of the position.
	 * @param y
	 *            The y-coordinate of the position.
	 * @param side
	 *            The side length of the square.
	 * @param c
	 *            The color of the square.
	 * @throws IllegalPositionException
	 *             Thrown if any of the coordinates are negative.
	 */
	public Square(int x, int y, int side, Color c)
			throws IllegalPositionException {
		this.x = x;
		this.y = y;
		this.side = side;
		this.color = c;
	}
	
	/**
	 * Construct a square with the same position as a given GeometricalForm, and
	 * the given side length and color.
	 * 
	 * @param f
	 *            The GeometricalForm which position to use.
	 * @param side
	 *            The side length of the square.
	 * @param c
	 *            The color of the square.
	 */
	public Square(GeometricalForm f, int side, Color c) {
		this.x = f.getX();
		this.y = f.getY();
		this.side = side;
		this.color = c;
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return side ^ 2;
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
		g.fillRect(x, y, side, side);
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
		return side;
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public int getHeight() {
		return side;
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
		return 4 * side;
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
