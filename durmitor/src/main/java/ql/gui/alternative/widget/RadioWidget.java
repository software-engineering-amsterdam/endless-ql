package ql.gui.alternative.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.gui.alternative.interfaces.Enumerable;
import ql.visitors.interfaces.TypeVisitor;

public class RadioWidget extends Widget<JPanel> implements TypeVisitor<JPanel> {

	private Map<Literal<?>,AbstractButton> buttons = new HashMap<Literal<?>,AbstractButton>();
	
	public RadioWidget() {
	}
	
	public RadioWidget(Type type) {
		component = type.accept(this);
	}
	
	@Override
	public void setValue(Literal<?> value) {
		for(Literal<?> literal : buttons.keySet()) {
			if(literal.getValue().equals(value.getValue())) {
				buttons.get(literal).setSelected(true);
			}
		}
	}

	@Override
	public String getValue() {
		
		for(AbstractButton button : buttons.values()) {
			if(button.isSelected()) {
				return button.getActionCommand();
			}
		}
		
		return "";
	}

	@Override
	public JPanel visit(Bool type) {
		return createComponentForType(type);
	}

	/*
	@Override
	public JPanel visit(Str type) {
		return createComponentForType(type);
	}

	@Override
	public JPanel visit(Int type) {
		return createComponentForType(type);
	}

	@Override
	public JPanel visit(Decimal type) {
		return createComponentForType(type);
	}

	@Override
	public JPanel visit(Money type) {
//		TODO: Make money enumerable
//		return createComponentForType(type);
		throw new Unassignable(this, type);
	}

	@Override
	public JPanel visit(Date type) {
		return createComponentForType(type);
	}
	*/
	
	private JPanel createComponentForType(Enumerable type) {
		
		if(type.getValues().length == 0)
		{
			throw new RuntimeException(String.format("This widget required at least 2 values"));
		}
		
		ButtonGroup group	= new ButtonGroup();
		JPanel panel			= new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(Literal<?> literal : type.getValues()) 
		{
			JRadioButton button = new JRadioButton(literal.toString());
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					notifyObservers();
				}
			});
			
			buttons.put(literal,button);
			panel.add(button);
			group.add(button);
		}
		
		group.clearSelection();
		
		return panel;
	}
}
