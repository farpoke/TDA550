package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a point.
 */
public class Point extends AbstractForm {
	
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
		super(x, y, 0, 0, c);
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
		super(f, 0, 0, c);
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
	public void fill(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getX(), getY(), 1, 1);
	}
	
	/**
	 * The perimeter of a point is defined as zero.
	 */
	@Override
	public int getPerimeter() {
		return 0;
	}
	
}
