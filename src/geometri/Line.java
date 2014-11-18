package geometri;

import java.awt.Color;
import java.awt.Graphics;

public class Line implements GeometricalForm {

	public Line(int x1, int y1, int x2, int y2, Color c)
			throws IllegalPositionException {
	}

	public Line(GeometricalForm f1, GeometricalForm f2, Color c) {
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
