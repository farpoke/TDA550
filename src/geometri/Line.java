package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Implementation of GeometricalForm that describes a line.
 */
public class Line extends AbstractForm {

	private boolean positiveSlope;

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
		super(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2)
				- Math.min(x1, x2), Math.max(y1, y2) - Math.min(y1, y2), c);
		positiveSlope = (x2 - x1) * (y2 - y1) >= 0;
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
	 * @throws IllegalPositionException 
	 * 	          Java demands this be here, but it can never happen.
	 */
	public Line(GeometricalForm f1, GeometricalForm f2, Color c) throws IllegalPositionException {
		super(
				Math.min(f1.getX(), f2.getX()),
				Math.min(f1.getY(), f2.getY()),
				Math.max(f1.getX(), f2.getX()) - Math.min(f1.getX(), f2.getX()),
				Math.max(f1.getY(), f2.getY()) - Math.min(f1.getY(), f2.getY()),
				c);
		positiveSlope = (f2.getX() - f1.getX()) * (f2.getY() - f1.getY()) >= 0;
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
	public void fill(Graphics g) {
		g.setColor(getColor());
		if (positiveSlope)
			g.drawLine(getX(), getY(), getX() + getWidth(), getY() + getHeight());
		else
			g.drawLine(getX(), getY() + getHeight(), getX() + getWidth(), getY());
	}
	
	/**
	 * Returns the perimeter of the line, which is defined as its length.
	 */
	@Override
	public int getPerimeter() {
		return (int) Math.round(Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight()));
	}

}
