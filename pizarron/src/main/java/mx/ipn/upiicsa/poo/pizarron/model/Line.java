package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {
	private static final Integer DEFAULT_SIZE = 100;
	private static final Color DEFAULT_BORDER_COLOR = Color.RED;
	private static final Integer DEFAULT_STROKE = 5;
	
	private Integer size;
	Graphics2D g2d;

	protected Line(Integer x, Integer y) {
		super(x, y);
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.size = DEFAULT_SIZE;
		this.stroke = DEFAULT_STROKE;
		super.width = size;
		super.width = stroke;
	}

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setColor(this.borderColor);
		g2d.setStroke(new BasicStroke((float) this.stroke));
		g2d.drawLine(this.x, this.y, this.x+this.size, this.y);
	}
	
	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		//TODO Redimencionar
	}
	
	public static Line getDefault(Integer x, Integer y) {
		return new Line(x, y);
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
		
}
