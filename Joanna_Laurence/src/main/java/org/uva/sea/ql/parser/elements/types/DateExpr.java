package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

import java.util.Calendar;
import java.util.Date;

public class DateExpr extends ASTNode {
    private Calendar date = Calendar.getInstance();

    public DateExpr(String day, String month, String year) {
        this.date.set(Calendar.YEAR, Integer.parseInt(year));
        this.date.set(Calendar.MONTH, Integer.parseInt(month));
        this.date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
    }

    public Calendar getDate() {
        return date;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doDateExpr(this);
    }

    public Type getType() {
        return new Type("date");
    }
}
