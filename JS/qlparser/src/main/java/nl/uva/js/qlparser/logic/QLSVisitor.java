package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLSBaseVisitor;
import nl.uva.js.qlparser.antlr.QLSParser;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.qls.style.Property;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.QuestionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.style.StyleRule;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.Expression;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class QLSVisitor extends QLSBaseVisitor{

    @Override
    public DataType visitDataType(QLSParser.DataTypeContext ctx) {
        return DataType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public WidgetType visitWidgetType(QLSParser.WidgetTypeContext ctx) {
        return WidgetType.builder().build();
    }

    @Override
    public Property visitProperty(QLSParser.PropertyContext ctx) {
        return Property.builder().build();
    }

    @Override
    public Object visitValue(QLSParser.ValueContext ctx) {
        return ctx.getText(); //TODO
    }

    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        return Stylesheet.builder()
                .name(ctx.NAME().getText())
                .styleExpressions(ctx.styleBlock().<LinkedList<Expression>>accept(this))
                .build();
    }

    @Override
    public LinkedList<Expression> visitStyleBlock(QLSParser.StyleBlockContext ctx) {
        return ctx.expression()
                .stream()
                .map(this::visitExpression)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Expression visitExpression(QLSParser.ExpressionContext ctx) {
        return (Expression) super.visitExpression(ctx);
    }

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        LinkedList<Section> sections = ctx.section()
                .stream()
                .map(this::visitSection)
                .collect(Collectors.toCollection(LinkedList::new));

        return Page.builder()
                .name(ctx.NAME().getText())
                .sections(sections)
                .build();
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        LinkedList<QuestionReference> questions = ctx.question()
                .stream()
                .map(this::visitQuestion)
                .collect(Collectors.toCollection(LinkedList::new));

        return Section.builder()
                .name((String) DataType.STRING.getValueOf().apply(ctx.STRVAL().getText()))
                .questions(questions)
                .build();
    }

    @Override
    public QuestionReference visitQuestion(QLSParser.QuestionContext ctx) {
        return QuestionReference.builder()
                .name(ctx.NAME().getText()) //TODO widgetstyle etc
                .build();
    }

    @Override
    public WidgetStyle visitWidgetStyle(QLSParser.WidgetStyleContext ctx) {
        return WidgetStyle.builder()
                .build();
    }

    @Override
    public StyleRule visitStyleRule(QLSParser.StyleRuleContext ctx) {
        return StyleRule.builder()
                .build();
    }

    @Override
    public DefaultStyle visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        return DefaultStyle.builder()
                .build();
    }
}
