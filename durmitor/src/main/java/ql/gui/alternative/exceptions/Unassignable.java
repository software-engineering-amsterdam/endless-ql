package ql.gui.alternative.exceptions;

import ql.ast.type.Type;
import ql.exceptions.QLException;
import ql.gui.alternative.widget.Widget;

public class Unassignable extends QLException {

	private static final long serialVersionUID = 4948093701975057904L;

	public Unassignable(Widget<?> widget, Type type) {
        
        message = String.format("Widget [%s] cannot be assigned to type [%s]",widget,type);
//        location	= op.getLocation();
    }
}
