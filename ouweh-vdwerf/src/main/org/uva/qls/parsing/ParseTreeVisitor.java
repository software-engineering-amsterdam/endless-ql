package org.uva.qls.parsing;

import antlr.generated.QLSBaseVisitor;
import antlr.generated.QLSParser;

public class ParseTreeVisitor extends QLSBaseVisitor {
    @Override
    public Object visitStylesheet(QLSParser.StylesheetContext ctx) {
        return super.visitStylesheet(ctx);
    }

    @Override
    public Object visitPage(QLSParser.PageContext ctx) {
        return super.visitPage(ctx);
    }

    @Override
    public Object visitSection(QLSParser.SectionContext ctx) {
        return super.visitSection(ctx);
    }

    @Override
    public Object visitSegment(QLSParser.SegmentContext ctx) {
        return super.visitSegment(ctx);
    }

    @Override
    public Object visitDefaultStatement(QLSParser.DefaultStatementContext ctx) {
        return super.visitDefaultStatement(ctx);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        return super.visitQuestion(ctx);
    }

    @Override
    public Object visitWidget(QLSParser.WidgetContext ctx) {
        return super.visitWidget(ctx);
    }

    @Override
    public Object visitRadio(QLSParser.RadioContext ctx) {
        return super.visitRadio(ctx);
    }

    @Override
    public Object visitCheckbox(QLSParser.CheckboxContext ctx) {
        return super.visitCheckbox(ctx);
    }

    @Override
    public Object visitDropdown(QLSParser.DropdownContext ctx) {
        return super.visitDropdown(ctx);
    }

    @Override
    public Object visitSlider(QLSParser.SliderContext ctx) {
        return super.visitSlider(ctx);
    }

    @Override
    public Object visitText(QLSParser.TextContext ctx) {
        return super.visitText(ctx);
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
