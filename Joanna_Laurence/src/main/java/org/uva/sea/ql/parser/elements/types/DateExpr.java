package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

public class DateExpr extends ASTNode {
    private int day;
    private int month;
    private int year;

    public DateExpr(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doDateExpr(this);
    }

    public Type getType() {
        return new Type("date");
    }
}
