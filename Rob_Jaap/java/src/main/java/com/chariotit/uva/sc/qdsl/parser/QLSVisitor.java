package com.chariotit.uva.sc.qdsl.parser;

import com.chariotit.uva.sc.qdsl.grammar.QLSBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLSParser;
import com.chariotit.uva.sc.qdsl.parser.exception.UnknownOptionException;
import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.qls.ast.node.Properties;
import com.chariotit.uva.sc.qdsl.qls.ast.node.*;
import com.chariotit.uva.sc.qdsl.qls.ast.node.property.*;
import com.chariotit.uva.sc.qdsl.qls.ast.node.widget.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;


/**
 * pages
 * - contain sections
 * - (opt) default
 *
 * section
 * - has title
 * - contains questions, sections
 * - (opt) default
 *
 * question
 * - label
 * - (opt) styling attrs
 *
 * stylint attrs
 * - width
 * - font
 * - fontsize
 * - color
 * - widget (without : )
 *
 * default
 * - { styling attrs }
 * or
 * - default type widget widgettype
 *
 * radio("yes", "no")
 * checkbox
 * spinbox
 *
 *
 * check widget type with field types
 *
 * @param <T>
 */
public class QLSVisitor<T> extends QLSBaseVisitor<AstNode> {

    private Integer lineNumber(ParserRuleContext ctx) {
        return ctx.getStart().getLine();
    }

    private Integer columnNumber(ParserRuleContext ctx) {
        return ctx.getStart().getCharPositionInLine();
    }

    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {

        String label = ctx.identifier().getText();
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext c : ctx.page()) {
            pages.add(visitPage(c));
        }


        return new Stylesheet(pages, label, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        String label = ctx.identifier().getText();
        List<Section> sections = new ArrayList<>();
        List<DefaultProperties> defaultProperties = new ArrayList<>();

        for (QLSParser.SectionContext c : ctx.section()) {
            sections.add(visitSection(c));
        }

        for (QLSParser.DefaultdefContext c : ctx.defaultdef()) {
            defaultProperties.add(visitDefaultdef(c));
        }

        return new Page(sections, label, defaultProperties, columnNumber(ctx), lineNumber(ctx));
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        List<SectionElement> sectionElements = new ArrayList<>();
        List<DefaultProperties> defaultProperties = new ArrayList<>();

        for (QLSParser.Section_elemContext c : ctx.section_elem()) {
            sectionElements.add(visitSection_elem(c));
        }

        for (QLSParser.DefaultdefContext c : ctx.defaultdef()) {
            defaultProperties.add(visitDefaultdef(c));
        }

        return new Section(sectionElements, defaultProperties, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public DefaultProperties visitDefaultdef(QLSParser.DefaultdefContext ctx) {
        if (ctx.blockdefault() != null) {
            return visitBlockdefault(ctx.blockdefault());
        } else if (ctx.linedefault() != null) {
            return visitLinedefault(ctx.linedefault());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public SectionElement visitSection_elem(QLSParser.Section_elemContext ctx) {
        if (ctx.section() != null) {
            return visitSection(ctx.section());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        String label = ctx.identifier().getText();
        Properties properties = new Properties(lineNumber(ctx), columnNumber(ctx));

        if (ctx.widgetproperty() != null) {
            properties.getProperties().add(visitWidgetproperty(ctx.widgetproperty()));
        }

        return new Question(label, properties, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public WidgetType visitWidget_type(QLSParser.Widget_typeContext ctx) {
        if (ctx.radiowidget() != null) {
            return visitRadiowidget(ctx.radiowidget());
        } else if (ctx.spinboxwidget() != null) {
            return visitSpinboxwidget(ctx.spinboxwidget());
        } else if (ctx.checkboxwidget() != null) {
            return visitCheckboxwidget(ctx.checkboxwidget());
        } else if (ctx.textwidget() != null) {
            return visitTextwidget(ctx.textwidget());
        } else if (ctx.sliderwidget() != null) {
            return visitSliderwidget(ctx.sliderwidget());
        } else if (ctx.dropdownwidget() != null) {
            return visitDropdownwidget(ctx.dropdownwidget());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public RadioWidget visitRadiowidget(QLSParser.RadiowidgetContext ctx) {
        if (ctx.STRING() != null) {
            return new RadioWidget(
                    ctx.STRING(0).getText(),
                    ctx.STRING(1).getText(),
                    lineNumber(ctx),
                    columnNumber(ctx)
            );
        }

        return new RadioWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public SpinboxWidget visitSpinboxwidget(QLSParser.SpinboxwidgetContext ctx) {
        return new SpinboxWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public CheckboxWidget visitCheckboxwidget(QLSParser.CheckboxwidgetContext ctx) {
        return new CheckboxWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public TextWidget visitTextwidget(QLSParser.TextwidgetContext ctx) {
        return new TextWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public SliderWidget visitSliderwidget(QLSParser.SliderwidgetContext ctx) {
        return new SliderWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public DropdownWidget visitDropdownwidget(QLSParser.DropdownwidgetContext ctx) {
        if (ctx.STRING() != null) {
            return new DropdownWidget(
                    ctx.STRING(0).getText(),
                    ctx.STRING(1).getText(),
                    lineNumber(ctx),
                    columnNumber(ctx)
            );
        }

        return new DropdownWidget(lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public AstNode visitIdentifier(QLSParser.IdentifierContext ctx) {
        return super.visitIdentifier(ctx);
    }

    @Override
    public DefaultProperties visitBlockdefault(QLSParser.BlockdefaultContext ctx) {
        List<Property> propertyList = new ArrayList<>();

        for (QLSParser.PropertyContext c : ctx.property()) {
            propertyList.add(visitProperty(c));
        }

        Properties properties = new Properties(propertyList, lineNumber(ctx), columnNumber(ctx));

        return new DefaultProperties(getExpressionType(ctx.type()), properties, lineNumber(ctx),
                columnNumber(ctx));
    }

    @Override
    public DefaultProperties visitLinedefault(QLSParser.LinedefaultContext ctx) {
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(visitProperty(ctx.property()));
        Properties properties = new Properties(propertyList, lineNumber(ctx), columnNumber(ctx));

        return new DefaultProperties(getExpressionType(ctx.type()), properties, lineNumber(ctx),
                columnNumber(ctx));
    }

    private ExpressionType getExpressionType(QLSParser.TypeContext ctx) {
        if (ctx.BOOLEAN_TYPE() != null) {
            return ExpressionType.BOOLEAN;
        } else if (ctx.INTEGER_TYPE() != null) {
            return ExpressionType.INTEGER;
        } else if (ctx.MONEY_TYPE() != null) {
            return ExpressionType.MONEY;
        } else if (ctx.STRING_TYPE() != null) {
            return ExpressionType.STRING;
        } else {
            throw new UnknownOptionException();
        }
    }


    @Override
    public Property visitProperty(QLSParser.PropertyContext ctx) {
        if (ctx.widgetproperty() != null) {
            return visitWidthproperty(ctx.widthproperty());
        } else if (ctx.widthproperty() != null) {
            return visitWidthproperty(ctx.widthproperty());
        } else if (ctx.fontproperty() != null) {
            return visitFontproperty(ctx.fontproperty());
        } else if (ctx.fontsizeproperty() != null) {
            return visitFontsizeproperty(ctx.fontsizeproperty());
        } else if (ctx.colorproperty() != null) {
            return visitColorproperty(ctx.colorproperty());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public WidgetProperty visitWidgetproperty(QLSParser.WidgetpropertyContext ctx) {
        return new WidgetProperty(
                visitWidget_type(ctx.widget_type()),
                lineNumber(ctx),
                columnNumber(ctx)
        );
    }

    @Override
    public WidthProperty visitWidthproperty(QLSParser.WidthpropertyContext ctx) {
        return new WidthProperty(Integer.parseInt(ctx.NUMBER().getText()), lineNumber(ctx),
                columnNumber(ctx));
    }

    @Override
    public FontProperty visitFontproperty(QLSParser.FontpropertyContext ctx) {
        return new FontProperty(ctx.STRING().getText(), lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public FontSizeProperty visitFontsizeproperty(QLSParser.FontsizepropertyContext ctx) {
        return new FontSizeProperty(Integer.parseInt(ctx.NUMBER().getText()), lineNumber(ctx),
                columnNumber(ctx));
    }

    @Override
    public ColorProperty visitColorproperty(QLSParser.ColorpropertyContext ctx) {
        return new ColorProperty(ctx.COLOR_CODE().getText(), lineNumber(ctx), columnNumber(ctx));
    }
}
