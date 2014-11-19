package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a rectangle.
 */
public class Rectangle implements GeometricalForm {
	private int x, y;
	private int width, height;
	private Color color;
	
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
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = c;
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
		this.x = f.getX();
		this.y = f.getY();
		this.width = width;
		this.height = height;
		this.color = c;
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public int getArea() {
		return width * height;
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
		g.fillRect(x, y, width, height);
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
		return 2 * (width + height);
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
