package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Shape {
	private static final Integer DEFAULT_SIDE = 100;
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.GREEN;
	private static final Color SELECT_COLOR = Color.CYAN;
	private static final Integer DEFAULT_STROKE = 2;

	private Integer side;
	Graphics2D g2d;

	protected Square(Integer x, Integer y) {
		super(x, y);
		this.fillColor = DEFAULT_FILL_COLOR;
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.selectColor = SELECT_COLOR;
		this.side = DEFAULT_SIDE;
		this.stroke = DEFAULT_STROKE;
		super.width = side;
		super.height = side;
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
		g2d.setStroke(new BasicStroke((float) super.stroke));
		g2d.drawRect(this.x, this.y, this.width, this.height);
		g2d.setColor(this.fillColor);
		g2d.fillRect(this.x, this.y, this.width, this.height);
	}

	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		if(side == Shape.E_RESIZE) {
			Integer finalX = getX() + getWidth();
			setWidth(getWidth() + (x - finalX));
			setHeight(getWidth() + (x - finalX));
		} else if(side == Shape.S_RESIZE) {
			Integer finalY = getY() + getHeight();
			setHeight(getHeight() + (y - finalY));
			setWidth(getHeight() + (y - finalY));
		} else if(side == Shape.W_RESIZE) {
			Integer finalX = getX() + getWidth();
			setX(x);
			setWidth(finalX - getX());
			setHeight(finalX - getX());
		} else if(side == Shape.N_RESIZE) {
			Integer finalY = getY() + getHeight();
			setY(y);
			setHeight(finalY - getY());
			setWidth(finalY - getY());
		}
		paint(g);
	}
	
	public static Square getDefault(Integer x, Integer y) {
		return new Square(x, y);
	}

	public Integer getSide() {
		return side;
	}

	public void setSide(Integer side) {
		this.side = side;
	}
}
