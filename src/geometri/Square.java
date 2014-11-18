package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Square implements GeometricalForm {

	private int x, y;
	private int side;
	private Color color;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param side
	 * @param c
	 * @throws IllegalPositionException
	 */
	public Square(int x, int y, int side, Color c)
			throws IllegalPositionException {
	}

	/**
	 * 
	 * @param f
	 * @param side
	 * @param c
	 */
	public Square(GeometricalForm f, int side, Color c) {
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
