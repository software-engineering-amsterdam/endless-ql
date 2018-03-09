package ql.gui.widget;

import javax.swing.JComponent;

import ql.visiting.value.Value;


public interface WidgetInterface {

	public JComponent getJComponent();
	public WidgetConfiguration getStyle();
	public void setStyle(WidgetConfiguration style);
	public Value getValue();
	public void setValue(Value value);

	public void setEditability(boolean editable);
	public void setVisibility(boolean visible);
}
