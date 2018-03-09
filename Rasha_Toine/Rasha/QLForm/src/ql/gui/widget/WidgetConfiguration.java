package ql.gui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class WidgetConfiguration {
	private Font font;
	private Color color;
	private Integer width;
	private Integer height;


	public WidgetConfiguration(Font font, Color color, Dimension dimension) {
		this.font = font;
		this.color = color;
		this.width = dimension.width;
		this.height = dimension.height;
	}

	public Font getFont() {
		return font;
	}
	
	public Color getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}