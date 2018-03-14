package org.uva.sea.qls.interpreter;

import org.uva.sea.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.ql.interpreter.dataObject.questionData.WidgetParameters;
import org.uva.sea.qls.parser.elements.Page;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.elements.QLSNode;
import org.uva.sea.qls.parser.elements.specification.Question;
import org.uva.sea.qls.parser.elements.specification.Section;
import org.uva.sea.qls.parser.elements.specification.Specification;
import org.uva.sea.qls.parser.elements.style.*;
import org.uva.sea.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class EvaluateDefaultStyle extends BaseStyleASTVisitor<Void> {

    private WidgetType widgetTypeToFind;

    private Style foundStyle = new Style();


    /**
     * Hide constructor
     */
    private EvaluateDefaultStyle() {

    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Fetcher {
        public Style findStyle(Section node, WidgetType widgetTypeToFind) throws InterruptedException {
            return getStyle(widgetTypeToFind, node.getSpecifications());
        }

        public Style findStyle(Page node, WidgetType widgetTypeToFind) throws InterruptedException {
            return getStyle(widgetTypeToFind, node.getSpecificationList());
        }

        private Style getStyle(WidgetType widgetTypeToFind, List<Specification> specifications) throws InterruptedException {
            Style returnStyle = new Style();
            EvaluateDefaultStyle fetcher = new EvaluateDefaultStyle();
            for(Specification specification : specifications) {
                Style elementStyle = fetcher.findStyle(specification, widgetTypeToFind);
                returnStyle.fillNullFields(elementStyle);
            }
            return returnStyle;
        }
    }

    /**
     * Find all default blocks inside element
     * @param node
     * @param widgetTypeToFind
     * @return
     * @throws InterruptedException
     */
    public Style findStyle(QLSNode node, WidgetType widgetTypeToFind) throws InterruptedException {
        this.widgetTypeToFind = widgetTypeToFind;
        node.accept(this);
        return this.foundStyle;
    }

    @Override
    public Void visit(org.uva.sea.qls.parser.elements.specification.DefaultStyle node) throws InterruptedException {

        WidgetType styleType = WidgetType.valueOf(node.getTypeName().toUpperCase());
        if(styleType != this.widgetTypeToFind)
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
                defaultStyle.setWidget(node.getWidgetParameters());
                return null;
            }

            @Override
            public Void visit(Width node) {
                defaultStyle.setWidth(node.getWidth());
                return null;
            }
        });

        if(this.foundStyle != null)
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
}
