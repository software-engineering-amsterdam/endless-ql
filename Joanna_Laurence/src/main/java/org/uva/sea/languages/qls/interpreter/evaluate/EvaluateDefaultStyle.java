package org.uva.sea.languages.qls.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QLWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.qls.parser.elements.Page;
import org.uva.sea.languages.qls.parser.elements.QLSNode;
import org.uva.sea.languages.qls.parser.elements.specification.DefaultStyle;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.elements.specification.Section;
import org.uva.sea.languages.qls.parser.elements.specification.Specification;
import org.uva.sea.languages.qls.parser.elements.style.*;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class EvaluateDefaultStyle extends BaseStyleASTVisitor<Void> {

    private WidgetType widgetTypeToFind;

    private Style foundStyle = new Style();


    /**
     * Hide constructor
     */
    private EvaluateDefaultStyle() {

    }

    /**
     * Find all default blocks inside element
     *
     * @param node
     * @param widgetTypeToFind
     * @return
     * @throws InterruptedException
     */
    public Style findStyle(QLSNode node, WidgetType widgetTypeToFind) {
        this.widgetTypeToFind = widgetTypeToFind;
        node.accept(this);
        return this.foundStyle;
    }

    @Override
    public Void visit(DefaultStyle node)  {

        WidgetType styleType = WidgetType.valueOf(node.getTypeName().toUpperCase());
        if (styleType != this.widgetTypeToFind)
            return null;

        Style defaultStyle = new Style();
        node.accept(new BaseStyleASTVisitor<Void>() {
            @Override
            public Void visit(Color node) {
                defaultStyle.setColor(node.getColorCode());
                return null;
            }

            @Override
            public Void visit(Font node) {
                defaultStyle.setFont(node.getName());
                return null;
            }

            @Override
            public Void visit(FontSize node) {
                defaultStyle.setFontSize(node.getSize());
                return null;
            }

            @Override
            public Void visit(Widget node) {
                defaultStyle.setWidget(new QLWidget(node.getWidgetType(), node.getStringParameters()));
                return null;
            }

            @Override
            public Void visit(Width node) {
                defaultStyle.setWidth(node.getWidth());
                return null;
            }
        });

        if (this.foundStyle != null)
            defaultStyle.fillNullFields(this.foundStyle);

        this.foundStyle = defaultStyle;
        return null;
    }

    @Override
    public Void visit(Question node) {
        return null;
    }

    @Override
    public Void visit(Section node) {
        return null;
    }


    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Fetcher {

        /**
         * Lookup style in parent sections and pages
         *
         * @param widgetType For what widget type the style has to be fetched
         * @return Cascading style
         */
        public Style getCascadingStyle(WidgetType widgetType, Stack<Section> inSection, Page inPage) {
            Style style = new Style();

            ListIterator<Section> li = inSection.listIterator(inSection.size());
            while (li.hasPrevious()) {
                Style defaultStyle = this.findStyle(li.previous(), widgetType);
                style.fillNullFields(defaultStyle);
            }
            Style pageStyle = this.findStyle(inPage, widgetType);
            style.fillNullFields(pageStyle);
            return style;
        }

        private Style findStyle(Section node, WidgetType widgetTypeToFind) {
            return getStyle(widgetTypeToFind, node.getSpecifications());
        }

        private Style findStyle(Page node, WidgetType widgetTypeToFind) {
            return getStyle(widgetTypeToFind, node.getSpecificationList());
        }

        private Style getStyle(WidgetType widgetTypeToFind, List<Specification> specifications) {
            Style returnStyle = new Style();
            EvaluateDefaultStyle fetcher = new EvaluateDefaultStyle();
            for (Specification specification : specifications) {
                Style elementStyle = fetcher.findStyle(specification, widgetTypeToFind);
                returnStyle.fillNullFields(elementStyle);
            }
            return returnStyle;
        }
    }
}
