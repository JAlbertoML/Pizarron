package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	private static final Integer DEFAULT_WIDTH = 100;
	private static final Integer DEFAULT_HEIGHT = 100;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.PINK;
	private static final Color SELECT_COLOR = Color.CYAN;
	private static final Integer DEFAULT_STROKE = 2;

	private Integer width;
	private Integer height;
	Graphics2D g2d;

	protected Triangle(Integer x, Integer y) {
		super(x, y);
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.selectColor = SELECT_COLOR;
		this.fillColor = DEFAULT_FILL_COLOR;
		this.stroke = DEFAULT_STROKE;
		super.width = this.width;
		super.height = this.height;
	}

	@Override
	public void paint(Graphics g) {
		int xCoord[] = { this.x + (this.width / 2), this.x, this.x + this.width };
		int yCoord[] = { this.y, this.y + this.height, this.y + this.height };
		g2d = (Graphics2D) g;
		if (selected) {
			g2d.setColor(this.selectColor);
			g2d.setStroke(new BasicStroke((float) 2));
			g2d.drawRect(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
		}
		g2d.setColor(this.borderColor);
		g2d.setStroke(new BasicStroke((float) this.stroke));
		g2d.drawPolygon(xCoord, yCoord, 3);
		g2d.setColor(fillColor);
		g2d.fillPolygon(xCoord, yCoord, 3);
	}
	
	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		if(side == Shape.E_RESIZE) {
			Integer finalX = getX() + getWidth();
			setWidth(getWidth() + (x - finalX));
		} else if(side == Shape.S_RESIZE) {
			Integer finalY = getY() + getHeight();
			setHeight(getHeight() + (y - finalY));
		} else if(side == Shape.W_RESIZE) {
			Integer finalX = getX() + getWidth();
			setX(x);
			setWidth(finalX - getX());
		} else if(side == Shape.N_RESIZE) {
			Integer finalY = getY() + getHeight();
			setY(y);
			setHeight(finalY - getY());
		}
		paint(g);
	}

	public static Triangle getDefault(Integer x, Integer y) {
		return new Triangle(x, y);
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
