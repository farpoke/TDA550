package geometri;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Oval implements GeometricalForm {

	private int x, y;
	private int width, height;
	private Color color;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param c
	 * @throws IllegalPositionException
	 */
	public Oval(int x, int y, int width, int height, Color c)
			throws IllegalPositionException {
	}

	/**
	 * 
	 * @param f
	 * @param width
	 * @param height
	 * @param c
	 */
	public Oval(GeometricalForm f, int width, int height, Color c) {
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
