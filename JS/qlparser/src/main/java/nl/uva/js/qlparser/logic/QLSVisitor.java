package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLSBaseVisitor;
import nl.uva.js.qlparser.antlr.QLSParser;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.models.qls.elements.ExpressionReference;
import nl.uva.js.qlparser.models.qls.elements.Section;
import nl.uva.js.qlparser.models.qls.enums.Property;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;
import nl.uva.js.qlparser.models.qls.style.DefaultStyle;
import nl.uva.js.qlparser.models.qls.style.StyleRule;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class QLSVisitor extends QLSBaseVisitor{

    @Override
    public DataType visitDataType(QLSParser.DataTypeContext ctx) {
        return DataType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public WidgetType visitWidgetType(QLSParser.WidgetTypeContext ctx) {
        return WidgetType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public Property visitProperty(QLSParser.PropertyContext ctx) {
        return Property.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        LinkedList<DefaultStyle> defaultStyles = null != ctx.defaultStyle() ?
                ctx.defaultStyle()
                .stream()
                .map(this::visitDefaultStyle)
                .collect(Collectors.toCollection(LinkedList::new))
                : null;

        LinkedList<Page> pages = null != ctx.page() ?
                ctx.page()
                .stream()
                .map(this::visitPage)
                .collect(Collectors.toCollection(LinkedList::new))
                : null;

        return Stylesheet.builder()
                .name(ctx.NAME().getText())
                .defaultStyles(defaultStyles)
                .pages(pages)
                .build();
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
        LinkedList<ExpressionReference> expressionReferences = ctx.expression()
                .stream()
                .map(this::visitExpression)
                .collect(Collectors.toCollection(LinkedList::new));

        return Section.builder()
                .name((String) DataType.STRING.getValueOf().apply(ctx.STRVAL().getText()))
                .expressionReferences(expressionReferences)
                .build();
    }

    @Override
    public ExpressionReference visitExpression(QLSParser.ExpressionContext ctx) {
        return ExpressionReference.builder()
                .name(ctx.NAME().getText())
                .widgetType((WidgetType) getOptional(ctx.widgetType()))
                .widgetStyle((WidgetStyle) getOptional(ctx.widgetStyle()))
                .build();
    }

    @Override
    public WidgetStyle visitWidgetStyle(QLSParser.WidgetStyleContext ctx) {
        LinkedList<StyleRule> styleRules = ctx.styleRule()
                .stream()
                .map(this::visitStyleRule)
                .collect(Collectors.toCollection(LinkedList::new));

        return WidgetStyle.builder()
                .styleRules(styleRules)
                .build();
    }

    @Override
    public StyleRule visitStyleRule(QLSParser.StyleRuleContext ctx) {
        return StyleRule.builder()
                .property(ctx.property().<Property>accept(this))
                .value((String) DataType.STRING.getValueOf().apply(ctx.STRVAL().getText()))
                .build();
    }

    @Override
    public DefaultStyle visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        return DefaultStyle.builder()
                .dataType(ctx.dataType().<DataType>accept(this))
                .widgetType(ctx.widgetType().<WidgetType>accept(this))
                .style((WidgetStyle) getOptional(ctx.widgetStyle()))
                .build();
    }

    private Object getOptional(ParserRuleContext ctx) {
        return (null != ctx)
               ? ctx.accept(this)
               : null;
    }
}
