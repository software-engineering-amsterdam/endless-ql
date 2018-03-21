package ql.gui.alternative.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.gui.alternative.interfaces.Enumerable;
import ql.visitors.interfaces.TypeVisitor;

public class DropdownWidget extends Widget<JComboBox<Literal<?>>> implements TypeVisitor<JComboBox<Literal<?>>> {

	public DropdownWidget() {
	}
	
	public DropdownWidget(Type type) {
		
		component = type.accept(this);
		
		if(component != null)
		{
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
		component.setSelectedItem(value);
	}

	@Override
	public String getValue() {
		return String.valueOf(component.getSelectedItem());
	}
	
	@Override
	public JComboBox<Literal<?>> visit(Bool type) {
		return createComponentForType(type);
	}

	/*
	@Override
	public JComboBox<Literal<?>> visit(Str type) {
		return createComponentForType(type);
	}

	@Override
	public JComboBox<Literal<?>> visit(Int type) {
		return createComponentForType(type);
	}

	@Override
	public JComboBox<Literal<?>> visit(Decimal type) {
		return createComponentForType(type);
	}

	@Override
	public JComboBox<Literal<?>> visit(Money type) {
		//TODO: make money enumerable
//		return createComponentForType(type);
		throw new Unassignable(this, type);
	}
	@Override
	public JComboBox<Literal<?>> visit(Date type) {
		return createComponentForType(type);
	}
	 */
	
	private JComboBox<Literal<?>> createComponentForType(Enumerable type) {
		
		if(type.getValues().length == 0)
		{
			throw new RuntimeException(String.format("This widget required at least 2 values"));
		}
		
		return new JComboBox<>(type.getValues());
	}
}
