package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Cloneable {
	public static final Integer E_RESIZE = 0;
	public static final Integer W_RESIZE = 1;
	public static final Integer N_RESIZE = 2;
	public static final Integer S_RESIZE = 3;
	
	protected Integer x;
	protected Integer y;
	protected Integer stroke;
	protected Color borderColor;
	protected Color fillColor;
	protected Color selectColor;
	protected Integer width;
	protected Integer height;
	protected Boolean selected = false;

	protected Shape(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getStroke() {
		return stroke;
	}

	public void setStroke(Integer stroke) {
		this.stroke = stroke;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
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

	public abstract void paint(Graphics g);
	
	public abstract void resize(Graphics g, Integer x, Integer y, Integer side);

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	public Color getSelectColor() {
		return selectColor;
	}

	public void setSelectColor(Color selectColor) {
		this.selectColor = selectColor;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
