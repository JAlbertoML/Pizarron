package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Image extends Shape {

	private static final Color DEFAULT_BORDER_COLOR = Color.WHITE;
	private static final Color DEFAULT_FILL_COLOR = Color.WHITE;
	private static final Color SELECT_COLOR = Color.CYAN;
	private static final Integer DEFAULT_STROKE = 5;

	private Integer width;
	private Integer height;
	private java.awt.Image img;
	Graphics2D g2d;

	protected Image(Integer x, Integer y, java.awt.Image img, Integer width, Integer height) {
		super(x, y);
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.selectColor = SELECT_COLOR;
		this.fillColor = DEFAULT_FILL_COLOR;
		this.width = width;
		this.height = height;
		this.stroke = DEFAULT_STROKE;
		this.img = img;
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

		g2d.setColor(DEFAULT_BORDER_COLOR);
		g2d.setStroke(new BasicStroke((float) DEFAULT_STROKE));
		g2d.drawImage(img, x, y, width, height, null);
	}

	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		if (side == Shape.E_RESIZE) {
			Integer finalX = getX() + getWidth();
			setWidth(getWidth() + (x - finalX));
		} else if (side == Shape.S_RESIZE) {
			Integer finalY = getY() + getHeight();
			setHeight(getHeight() + (y - finalY));
		} else if (side == Shape.W_RESIZE) {
			Integer finalX = getX() + getWidth();
			setX(x);
			setWidth(finalX - getX());
		} else if (side == Shape.N_RESIZE) {
			Integer finalY = getY() + getHeight();
			setY(y);
			setHeight(finalY - getY());
		}
		paint(g);
	}

	public static Image getDefault(Integer x, Integer y, java.awt.Image img) {
		return new Image(x, y, img, img.getWidth(null) / 3, img.getHeight(null) / 3);
	}

	public java.awt.Image getImg() {
		return img;
	}

	public void setImg(java.awt.Image img) {
		this.img = img;
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
