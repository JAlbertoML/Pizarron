package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pencil extends Shape{
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Integer DEFAULT_STROKE = 1;
	private List<Point> points;
	Graphics2D g2d;
	
	protected Pencil(Integer x, Integer y) {
		super(x, y);
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.stroke = DEFAULT_STROKE;
		points = new ArrayList<Point>();
		points.add(new Point(x, y));
	}
	
	@Override
	public void paint(Graphics g) {
		if (points.size() > 2) {
			g2d = (Graphics2D) g;
			g2d.setColor(this.borderColor);
			g2d.setStroke(new BasicStroke((float) this.stroke));
			Point a = points.get(0);
			for(int i = 1; i < points.size(); i++) {
				Point b = points.get(i);
				g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
				a = b;
			}
		}
	}
	
	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		//TODO Supongo que no se puede redimensionar
	}
	
	public static Pencil getDefault(Integer x, Integer y) {
		return new Pencil(x, y);
	}
	
	public void addPoint(Integer x, Integer y) {
		points.add(new Point(x, y));
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
}
