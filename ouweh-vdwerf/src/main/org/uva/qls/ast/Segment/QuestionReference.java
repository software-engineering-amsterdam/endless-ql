package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.Widget;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionReference extends Segment {

    private String id;
    private Style style;
    private Widget widget;

    public QuestionReference(String id, Style style, Widget widget) {
        this.id = id;
        this.style = style;
        this.widget = widget;
    }

    @Override
    public String getId() {
        return "Question." + id;
    }

    public String getQuestionId() {
        return id;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public List<DefaultStyleStatement> getDefaultStyleStatements() {
        return new ArrayList<>();
    }

    @Override
    public List<DefaultWidgetStatement> getDefaultWidgetStatements() {
        return new ArrayList<>();
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this, parent);
    }
}
