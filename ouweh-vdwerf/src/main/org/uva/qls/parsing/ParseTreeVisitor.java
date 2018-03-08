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
