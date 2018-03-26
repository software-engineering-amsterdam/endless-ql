package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

import java.util.Calendar;

public class DateExpr extends Expression {
    private final Calendar date = Calendar.getInstance();

    public DateExpr(final Token token, final String day, final String month, final String year) {
        super(token);
        this.date.set(Calendar.YEAR, Integer.parseInt(year));
        this.date.set(Calendar.MONTH, Integer.parseInt(month));
        this.date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
    }

    public final Calendar getDate() {
        return this.date;
    }

    public final Type getType() {
        return new Type(NodeType.DATE);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
