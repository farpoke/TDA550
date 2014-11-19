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
		this.x = x;
		this.y = y;
		this.color = c;
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
		this.x = f.getX();
		this.y = f.getY();
		this.color = c;
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
		if (f.getArea() > 0)
			return -1;
		else if (f.getPerimeter() > 0)
			return -1;
		else
			return 0;
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public void fill(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 1, 1);
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public Color getColor() {
		return color;
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
		if (x < 0 || y < 0)
			throw new IllegalPositionException(String.format(
					"Illegal position (%d; %d)", x, y));
		this.x = x;
		this.y = y;
	}
}
