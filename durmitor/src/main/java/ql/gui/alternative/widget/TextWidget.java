package ql.gui.alternative.widget;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ql.ast.expression.literal.Literal;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.visitors.interfaces.TypeVisitor;

public class TextWidget extends Widget<JTextField> implements TypeVisitor<JTextField> {

	public TextWidget() {}
	
	public TextWidget(Type type) {
		
		component = type.accept(this);
		
		if(component != null) component.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				notifyObservers();
			}
		});
	}

	@Override
	public void setValue(Literal<?> value) {
		component.setText(String.valueOf(value.getValue()));
	}

	@Override
	public String getValue() {
		return component.getText();
	}

	@Override
	public JTextField visit(Str type) {
		return new JTextField();
	}

	@Override
	public JTextField visit(Int type) {
		JTextField textField = new JTextField();
		textField.setDocument(new InputLimiter("^[0-9]+$"));
		return textField;
	}

	@Override
	public JTextField visit(Decimal type) {
		JTextField textField = new JTextField();
		textField.setDocument(new InputLimiter("^[0-9]+[.]?[0-9]*$"));
		return textField;
	}

	@Override
	public JTextField visit(Money type) {
		JTextField textField = new JTextField();
		textField.setDocument(new InputLimiter("^[0-9]+[.]?[0-9]{0,2}$"));
		return textField;
	}

	private class InputLimiter extends PlainDocument {
		
		private static final long serialVersionUID = -3844769295131395870L;
		
		private String regex = "";
		
		public InputLimiter(String regex) {
			this.regex = regex;
		}
		
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if(getText(0, offs).concat(str).matches(regex)) super.insertString(offs, str, a);
		}
	}
}
