package ql.gui.alternative.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.TypeVisitor;

public class CheckboxWidget extends Widget<JCheckBox> implements TypeVisitor<JCheckBox> {

	public CheckboxWidget() {}
	
	public CheckboxWidget(Type type) {
		
		component = type.accept(this);
		
		if(component != null)
		{
			component.setSelected(false);
			component.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					notifyObservers();
				}
			});
		}
	}
	
	@Override
	public void setValue(Literal<?> value) {
		component.setSelected(Bool.parseBool(value).getValue());
	}

	@Override
	public String getValue() {
		return String.valueOf(component.isSelected());
	}

	@Override
	public JCheckBox visit(Bool type) {
		return new JCheckBox();
	}
}
