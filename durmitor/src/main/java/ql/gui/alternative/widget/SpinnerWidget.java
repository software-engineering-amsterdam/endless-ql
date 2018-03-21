package ql.gui.alternative.widget;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Type;
import ql.gui.alternative.exceptions.Unassignable;
import ql.gui.alternative.interfaces.Enumerable;
import ql.helpers.Observer;

public class SpinnerWidget extends Widget<JSpinner> {
	
	public SpinnerWidget() {}
	
	public static SpinnerWidget createSpinner(Type type) {
		
		if(type.isDate()) return new DateSpinnerWidget((Date) type);
		
		return new SpinnerWidget(type);
	}
	
	private SpinnerWidget(Type type) {
		
		System.out.println("SpinnerWidget @ 38");
		
		component = type.accept(new SpinnerWidget(type));
		
		if(component != null)
		{
			component.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					System.out.println("SpinnerWidget.stateChanged()");
					notifyObservers();
				}
			});
		}
	}
	
	@Override
	public void notifyObservers() {
		System.out.println(hashCode());
		System.out.println("notifyObservers("+this+")");
		for(Observer o : observers) o.update(this, new Literal<?>[0]);
	}
	
	@Override
	public void setValue(Literal<?> value) {
		
		if(value.getType().isInteger())
		{
			component.setValue(Int.parseInt(value).getValue());
		}
	}

	@Override
	public String getValue() {
		System.out.println("SpinnerWidget: "+component.getValue());
		return "";
	}

	@Override
	public JSpinner visit(Bool type) {
		return createBoundedComponentForType(type);
	}

	/*
	@Override
	public JSpinner visit(Str type) {
		return createBoundedComponentForType(type);
	}

	@Override
	public JSpinner visit(Int type) {
		
		if(type.getStepSize().getValue() <= 0)
		{
			SpinnerNumberModel model = new SpinnerNumberModel();
			model.setMinimum(type.getMinimum().getValue());
			model.setMaximum(type.getMaximum().getValue());
			model.setStepSize(type.getStepSize().getValue());
			return new JSpinner(model);
		}
		
		return createBoundedComponentForType(type);
	}

	@Override
	public JSpinner visit(Decimal type) {
		return createBoundedComponentForType(type);
	}
	*/

	@Override
	public JSpinner visit(Money type) {
//		TODO: Make money enumerable
//		return createBoundedComponentForType(type);
		throw new Unassignable(this, type);
	}

	private JSpinner createBoundedComponentForType(Enumerable type) {
		
		if(type.getValues().length == 0)
		{
			throw new RuntimeException(String.format("This widget required at least 2 values"));
		}
		
		return new JSpinner(new SpinnerListModel(type.getValues()));
	}
}
