package mx.ipn.upiicsa.poo.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Text extends Shape{
	
	private static final Color DEFAULT_TEXT_COLOR = Color.BLUE;
	private static final String DEFAULT_TEXT = "Some text";
	private String str;
	Graphics2D g2d;
	
	protected Text(Integer x, Integer y) {
		super(x, y);
		fillColor = DEFAULT_TEXT_COLOR;
		str = DEFAULT_TEXT;
	}

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setColor(this.fillColor);
		g2d.drawString(str, this.x, this.y);
		
	}
	
	public void resize(Graphics g, Integer x, Integer y, Integer side) {
		//TODO Redimensionar
	}
	
	public static Text getDefault(Integer x, Integer y) {
		return new Text(x, y);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
