package org.uva.qls.parsing;

import antlr.generated.QLSBaseVisitor;
import antlr.generated.QLSParser;

import org.uva.qls.ast.*;
import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.ast.Segment.Question;
import org.uva.qls.ast.Segment.Section;
import org.uva.qls.ast.Segment.Segment;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class ParseTreeVisitor extends QLSBaseVisitor {
    @Override
    public Object visitStylesheet(QLSParser.StylesheetContext ctx) {
        String styleSheetId = ctx.id.getText();
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return new Stylesheet(styleSheetId, pages);
    }

    @Override
    public Object visitPage(QLSParser.PageContext ctx) {
        String pageId = ctx.id.getText();

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStatement> defaultStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            defaultStatements.add((DefaultStatement) visit(defaultStatementContext));
        }

        return new Page(pageId, segments, defaultStatements);
    }

    @Override
    public Object visitSection(QLSParser.SectionContext ctx) {
        String pageId = ctx.id.getText();

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStatement> defaultStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            defaultStatements.add((DefaultStatement) visit(defaultStatementContext));
        }

        return new Section(pageId, segments, defaultStatements);
    }

    @Override
    public Object visitSegment(QLSParser.SegmentContext ctx) {
        if(ctx.section() != null) {
            return (Section) visit(ctx.section());
        }
        return (Question) visit(ctx.question());
    }

    @Override
    public Object visitDefaultStatement(QLSParser.DefaultStatementContext ctx) {
        return super.visitDefaultStatement(ctx);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.id.getText();
        Style style = null;
        Widget widget = null;

        if(ctx.style() != null) {
            style = (Style) visit(ctx.style());
        } else if (ctx.widget() != null) {
            widget = (Widget) visit(ctx.widget());
        }

        return new Question(id, style, widget);
    }

    @Override
    public Object visitWidget(QLSParser.WidgetContext ctx) {
        return super.visitWidget(ctx);
    }

    @Override
    public Object visitRadioType(QLSParser.RadioTypeContext ctx) {
        return super.visitRadioType(ctx);
    }

    @Override
    public Object visitCheckboxType(QLSParser.CheckboxTypeContext ctx) {
        return super.visitCheckboxType(ctx);
    }

    @Override
    public Object visitDropdownType(QLSParser.DropdownTypeContext ctx) {
        return super.visitDropdownType(ctx);
    }

    @Override
    public Object visitSliderType(QLSParser.SliderTypeContext ctx) {
        return super.visitSliderType(ctx);
    }

    @Override
    public Object visitTextType(QLSParser.TextTypeContext ctx) {
        return super.visitTextType(ctx);
    }

    @Override
    public Object visitBooleanType(QLSParser.BooleanTypeContext ctx) {
        return super.visitBooleanType(ctx);
    }

    @Override
    public Object visitIntegerType(QLSParser.IntegerTypeContext ctx) {
        return super.visitIntegerType(ctx);
    }

    @Override
    public Object visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        return super.visitMoneyType(ctx);
    }

    @Override
    public Object visitStringType(QLSParser.StringTypeContext ctx) {
        return super.visitStringType(ctx);
    }

    @Override
    public Object visitStyle(QLSParser.StyleContext ctx) {
        return super.visitStyle(ctx);
    }

    @Override
    public Object visitStyleProperty(QLSParser.StylePropertyContext ctx) {
        return super.visitStyleProperty(ctx);
    }

    @Override
    public Object visitValue(QLSParser.ValueContext ctx) {
        return super.visitValue(ctx);
    }
}
