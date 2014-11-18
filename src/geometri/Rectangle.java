package geometri;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements GeometricalForm {

	public Rectangle(int x, int y, int width, int height, Color c)
			throws IllegalPositionException {
	}

	public Rectangle(GeometricalForm f, int width, int height, Color c) {
	}

	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(GeometricalForm f) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(int dx, int dy) throws IllegalPositionException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPerimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void place(int x, int y) throws IllegalPositionException {
		// TODO Auto-generated method stub

	}

}
