package ql.gui.alternative.widget;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Date;

public class DateSpinnerWidget extends SpinnerWidget {
	
	private java.util.Date value	= new java.util.Date(); 
	private SimpleDateFormat formatter;

	public DateSpinnerWidget(Date type) {
		
		SpinnerDateModel model = new SpinnerDateModel();
		
		component = new JSpinner(model);
		formatter = ((DateEditor) component.getEditor()).getFormat();
		
		formatter.applyPattern(ResourceBundle.getBundle("ql.i18n.date").getString("date.pattern"));
		component.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("DateSpinnerWidget.stateChanged()");
				value = (java.util.Date) component.getValue();
				notifyObservers();
			}
		});
	}
	
	@Override
	public void setValue(Literal<?> value) {
		
		if(value.getType().isDate())
		{
			component.setValue(value.getValue());
		}
	}
	
	@Override
	public String getValue() {
		System.out.println("DateSpinnerWidget: "+component.getValue());
		return String.valueOf(formatter.format(value));
	}
	
	@Override
	public JSpinner visit(Date type) {
		System.out.println("63");
		return new JSpinner();
	}
}
