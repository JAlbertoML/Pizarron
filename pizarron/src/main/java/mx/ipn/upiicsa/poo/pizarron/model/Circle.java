package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape {
	private static final Integer DEFAULT_DIAMETER = 100;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
	private static final Color SELECT_COLOR = Color.CYAN;
	private static final Integer DEFAULT_STROKE = 2;

	private Integer diameter;
	Graphics2D g2d;

	protected Circle(Integer x, Integer y) {
		super(x, y);
		this.fillColor = DEFAULT_FILL_COLOR;
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.diameter = DEFAULT_DIAMETER;
		this.selectColor = SELECT_COLOR;
		this.stroke = DEFAULT_STROKE;
		super.width = diameter;
		super.height = diameter;
	}

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		if (selected) {
			g2d.setColor(this.selectColor);
			g2d.setStroke(new BasicStroke((float) this.stroke + 5));
			g2d.drawOval(x - 2, y - 2, width + 4, height + 4);
		}
		g2d.setColor(this.borderColor);
		g2d.setStroke(new BasicStroke((float) this.stroke));
		g2d.drawOval(x, y, width, height);
		g2d.setColor(this.fillColor);
		g2d.fillOval(this.x, this.y, this.width, this.height);
	}
	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		if (side == Shape.E_RESIZE) {
			Integer finalX = getX() + getWidth();
			setWidth(getWidth() + (x - finalX));
			setHeight(getWidth() + (x - finalX));
		} else if (side == Shape.S_RESIZE) {
			Integer finalY = getY() + getHeight();
			setHeight(getHeight() + (y - finalY));
			setWidth(getHeight() + (y - finalY));
		} else if (side == Shape.W_RESIZE) {
			Integer finalX = getX() + getWidth();
			setX(x);
			setWidth(finalX - getX());
			setHeight(finalX - getX());
		} else if (side == Shape.N_RESIZE) {
			Integer finalY = getY() + getHeight();
			setY(y);
			setHeight(finalY - getY());
			setWidth(finalY - getY());
		}
		paint(g);
	}

	public static Circle getDefault(Integer x, Integer y) {
		return new Circle(x, y);
	}

	public Integer getDiameter() {
		return diameter;
	}

	public void setDiameter(Integer diameter) {
		this.diameter = diameter;
	}
}
