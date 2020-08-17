package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
	private static final Integer DEFAULT_WIDTH = 125;
	private static final Integer DEFAULT_HEIGHT = 75;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.CYAN;
	private static final Color SELECT_COLOR = Color.CYAN;
	private static final Integer DEFAULT_STROKE = 2;

	Graphics2D g2d;

	protected Rectangle(Integer x, Integer y) {
		super(x, y);
		this.fillColor = DEFAULT_FILL_COLOR;
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.selectColor = SELECT_COLOR;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.stroke = DEFAULT_STROKE;
		super.width = this.width;
		super.height = this.height;
	}

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		if (selected) {
			g2d.setColor(this.selectColor);
			g2d.setStroke(new BasicStroke((float) this.stroke + 5));
			g2d.drawRect(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
		}
		g2d.setColor(this.borderColor);
		g2d.setStroke(new BasicStroke((float) this.stroke));
		g2d.drawRect(this.x, this.y, this.width, this.height);
		g2d.setColor(this.fillColor);
		g2d.fillRect(this.x, this.y, this.width, this.height);
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
	

	public static Rectangle getDefault(Integer x, Integer y) {
		return new Rectangle(x, y);
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
