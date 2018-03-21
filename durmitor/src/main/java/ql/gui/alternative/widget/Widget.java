package ql.gui.alternative.widget;

import java.awt.Container;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;

import ql.ast.expression.literal.Literal;
import ql.ast.statement.Question;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.gui.alternative.exceptions.Unassignable;
import ql.helpers.Observable;
import ql.helpers.Observer;
import ql.visitors.interfaces.TypeVisitor;

public abstract class Widget<C extends JComponent> implements TypeVisitor<C>, Observable {

	protected C component;
	protected Set<Observer> observers = new HashSet<Observer>();
	
	public Widget() {}
	
	public static Widget<?> create(Type type) {
		return type.accept(new DefaultWidget());
	}
	
	public static Widget<?> create(Question question) {
		
		if(question.isAnswerable())
		{
			Widget<?> widget = create(question.getIdentifier().getType());
			
			widget.getComponent().setName(question.getIdentifier().getName());
			
			return widget;
		}
		
		return new ComputedWidget(question);
	}
	
	public abstract void setValue(Literal<?> value);
	
	public abstract String getValue();
	
	public C getComponent() {
		return component;
	}
	
	public Container getContainer() {
		return component.getParent();
	}
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : observers) o.update(this, new Literal<?>[0]);
	}

	@Override
	public C visit(Bool type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Str type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Int type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Decimal type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Money type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Date type) {
		throw new Unassignable(this, type);
	}

	@Override
	public C visit(Undefined type) {
		throw new Unassignable(this, type);
	}
}
