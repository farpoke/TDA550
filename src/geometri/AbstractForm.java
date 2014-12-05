package geometri;

import java.awt.Color;

public abstract class AbstractForm implements GeometricalForm {

	private Color color;
	private int x, y;
	private int width, height;

	public AbstractForm(int x, int y, int width, int height, Color c)
			throws IllegalPositionException {
		place(x, y);
		this.width = width;
		this.height = height;
		this.color = c;
	}

	public AbstractForm(GeometricalForm f, int width, int height, Color c) {
		this.x = f.getX();
		this.y = f.getY();
		this.width = width;
		this.height = height;
		this.color = c;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		else if (obj == null)
			return false;
		else if (!obj.getClass().equals(this.getClass()))
			return false;
		else {
			AbstractForm f = (AbstractForm) obj;
			return (color == f.color) && (width == f.width) && (height == f.height);
		}
	}

	@Override
	public int hashCode() {
		// Linear combination with prime coefficients.
		return color.hashCode() * 103099 + width * 103123 + height * 103141;
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
	public Color getColor() {
		return color;
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
	public void move(int dx, int dy) throws IllegalPositionException {
		place(x + dx, y + dy);
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
