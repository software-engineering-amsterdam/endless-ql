package qls.ast;

import qls.ast.model.*;
import qls.ast.model.properties.*;
import qls.ast.model.properties.widgets.*;
import qls.grammar.QLSBaseVisitor;
import qls.grammar.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends QLSBaseVisitor<ASTNode> {
    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {

        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageDefinitionContext pageDefinitionContext : ctx.pageDefinition()) {
            pages.add(this.visitPageDefinition(pageDefinitionContext));
        }

        return new Stylesheet(
                new ASTNode.MetaInformation(ctx.start.getLine()),
                ctx.name.getText(),
                pages
        );
    }

    @Override
    public Page visitPageDefinition(QLSParser.PageDefinitionContext ctx) {
        List<BlockElement> elements = new ArrayList<>();

        for (QLSParser.BlockElementContext blockElementContext : ctx.blockElement()) {
            elements.add(this.visitBlockElement(blockElementContext));
        }

        return new Page(
                new ASTNode.MetaInformation(ctx.start.getLine()),
                ctx.name.getText(),
                elements
        );
    }

    @Override
    public BlockElement visitBlockElement(QLSParser.BlockElementContext ctx) {
        if (ctx.questionDefinition() != null)
            return this.visitQuestionDefinition(ctx.questionDefinition());
        if (ctx.section() != null)
            return this.visitSection(ctx.section());
        if (ctx.defaultDefinition() != null)
            return this.visitDefaultDefinition(ctx.defaultDefinition());
        return null;
    }

    @Override
    public QuestionDefinition visitQuestionDefinition(QLSParser.QuestionDefinitionContext ctx) {
        if (ctx.widgetDefinition() != null) {
            return new QuestionDefinition(
                    new ASTNode.MetaInformation(ctx.start.getLine()),
                    ctx.name.getText(),
                    this.visitWidget(ctx.widgetDefinition().widget())
            );
        }
        return new QuestionDefinition(
                new ASTNode.MetaInformation(ctx.start.getLine()),
                ctx.name.getText()
        );
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {

        List<BlockElement> elements = new ArrayList<>();

        if (ctx.questionDefinition() != null) {
            elements.add(this.visitQuestionDefinition(ctx.questionDefinition()));
        } else {
            for (QLSParser.BlockElementContext blockElementContext : ctx.blockElement()) {
                BlockElement element = this.visitBlockElement(blockElementContext);
                elements.add(element);
            }
        }

        return new Section(
                new ASTNode.MetaInformation(ctx.start.getLine()),
                ctx.name.getText(),
                elements
        );
    }


    @Override
    public DefaultDefinition visitDefaultDefinition(QLSParser.DefaultDefinitionContext ctx) {

        List<TypeProperty> properties = new ArrayList<>();

        if (ctx.widgetDefinition() != null)
            properties.add(visitWidget(ctx.widgetDefinition().widget()));
        if (ctx.typeDefinitionProperty() != null) {
            for (QLSParser.TypeDefinitionPropertyContext propertyContext : ctx.typeDefinitionProperty()) {
                properties.add(this.visitTypeDefinitionProperty(propertyContext));
            }
        }

        return new DefaultDefinition(
                new ASTNode.MetaInformation(ctx.start.getLine()),
                ctx.type.getText(),
                properties
        );
    }

    @Override
    public TypeProperty visitTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx) {
        if (ctx.width != null)
            return new WidthProperty(
                    new ASTNode.MetaInformation(ctx.start.getLine()),
                    Integer.parseInt(ctx.width.getText())
            );
        if (ctx.fontSize != null)
            return new FontSizeProperty(
                    new ASTNode.MetaInformation(ctx.start.getLine()),
                    Integer.parseInt(ctx.fontSize.getText())
            );
        if (ctx.font != null)
            return new FontProperty(
                    new ASTNode.MetaInformation(ctx.start.getLine()),
                    ctx.font.getText()
            );
        if (ctx.color != null)
            return new ColorProperty(
                    new ASTNode.MetaInformation(ctx.start.getLine()),
                    ctx.color.getText()
            );
        if (ctx.widgetDefinition() != null)
            return this.visitWidget(ctx.widgetDefinition().widget());
        return null;
    }

    @Override
    public Widget visitWidget(QLSParser.WidgetContext ctx) {
        if (ctx.WIDGET_BOOL_CHECKBOX() != null) {
            if (ctx.booleanParameters() != null) {
                return new CheckboxWidget(
                        new ASTNode.MetaInformation(ctx.start.getLine()),
                        new Widget.BooleanParameters(
                                ctx.booleanParameters().trueValue.getText(),
                                ctx.booleanParameters().falseValue.getText()
                        )
                );
            }
            return new CheckboxWidget(new ASTNode.MetaInformation(ctx.start.getLine())
            );
        }
        if (ctx.WIDGET_BOOL_DROPDOWN() != null) {
            if (ctx.booleanParameters() != null) {
                return new DropdownWidget(
                        new ASTNode.MetaInformation(ctx.start.getLine()),
                        new Widget.BooleanParameters(
                                ctx.booleanParameters().trueValue.getText(),
                                ctx.booleanParameters().falseValue.getText()
                        )
                );
            }
            return new DropdownWidget(new ASTNode.MetaInformation(ctx.start.getLine()));
        }
        if (ctx.WIDGET_BOOL_RADIO() != null) {
            if (ctx.booleanParameters() != null) {
                return new RadioWidget(
                        new ASTNode.MetaInformation(ctx.start.getLine()),
                        new Widget.BooleanParameters(
                                ctx.booleanParameters().trueValue.getText(),
                                ctx.booleanParameters().falseValue.getText()
                        )
                );
            }
            return new RadioWidget(new ASTNode.MetaInformation(ctx.start.getLine()));
        }
        if (ctx.WIDGET_INTEGER_SPINBOX() != null) {
            if (ctx.integerParameters() != null) {
                return new SpinboxWidget(
                        new ASTNode.MetaInformation(ctx.start.getLine()),
                        new Widget.IntegerParameters(
                                Integer.parseInt(ctx.integerParameters().min.getText()),
                                Integer.parseInt(ctx.integerParameters().max.getText()),
                                Integer.parseInt(ctx.integerParameters().step.getText())
                        )
                );
            }
            return new SpinboxWidget(new ASTNode.MetaInformation(ctx.start.getLine()));
        }
        if (ctx.WIDGET_INTEGER_SLIDER() != null) {
            if (ctx.integerParameters() != null) {
                return new SliderWidget(
                        new ASTNode.MetaInformation(ctx.start.getLine()),
                        new Widget.IntegerParameters(
                                Integer.parseInt(ctx.integerParameters().min.getText()),
                                Integer.parseInt(ctx.integerParameters().max.getText()),
                                Integer.parseInt(ctx.integerParameters().step.getText())
                        )
                );
            }
            return new SliderWidget(new ASTNode.MetaInformation(ctx.start.getLine()));
        }
        if (ctx.WIDGET_TEXT() != null) {
            return new TextWidget(new ASTNode.MetaInformation(ctx.start.getLine()));
        }
        return null;
    }
}
