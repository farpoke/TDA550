package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Line implements GeometricalForm {

	private int x1, y1;
	private int x2, y2;
	private Color color;
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param c
	 * @throws IllegalPositionException
	 */
	public Line(int x1, int y1, int x2, int y2, Color c)
			throws IllegalPositionException {
	}

	/**
	 * 
	 * @param f1
	 * @param f2
	 * @param c
	 */
	public Line(GeometricalForm f1, GeometricalForm f2, Color c) {
	}
	
	/**
	 * @inheritDoc
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
	 * @inheritDoc
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
