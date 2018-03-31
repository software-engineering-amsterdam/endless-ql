package ql.gui.alternative.widget;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Int;
import ql.ast.type.Type;
import ql.visitors.interfaces.TypeVisitor;

public class SliderWidget extends Widget<JSlider> implements TypeVisitor<JSlider> {

	public SliderWidget() {
	}
	
	public SliderWidget(Type type) {
		
		component = type.accept(this);
		
		if(component != null)
		{
			component.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					notifyObservers();
				}
			});
		}
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
		return String.valueOf(component.getValue());
	}

	@Override
	public JSlider visit(Int type) {
		
//		if(type.getMinimum().getValue().equals(type.getMaximum().getValue()))
//		{
//			throw new RuntimeException(String.format("This widget required the minimum and maximum to be different"));
//		}
//		
//		return new JSlider(type.getMinimum().getValue(), type.getMaximum().getValue());
	    
	    return new JSlider();
	}
}
