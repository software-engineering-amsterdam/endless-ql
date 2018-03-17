package org.uva.sea.languages.qls.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QLWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.parser.NodeType;
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

public class EvaluateDefaultStyle extends BaseStyleASTVisitor<Void> {

    private NodeType nodeTypeToFind = null;

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
     * @return
     * @throws InterruptedException
     */
    public Style findStyle(QLSNode node, NodeType nodeTypeToFind) {
        this.nodeTypeToFind = nodeTypeToFind;
        node.accept(this);
        return this.foundStyle;
    }

    @Override
    public Void visit(DefaultStyle style) {

        NodeType styleType = NodeType.valueOf(style.getTypeName().toUpperCase());
        if (styleType != this.nodeTypeToFind)
            return null;

        Style defaultStyle = new Style();
        style.accept(new BaseStyleASTVisitor<Void>() {
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
         * @param nodeType For what widget type the style has to be fetched
         * @return Cascading style
         */
        public Style getCascadingStyle(NodeType nodeType, List<Section> inSection, Page inPage) {
            Style style = new Style();

            ListIterator<Section> li = inSection.listIterator(inSection.size());
            while (li.hasPrevious()) {
                Style defaultStyle = this.findStyle(li.previous(), nodeType);
                style.fillNullFields(defaultStyle);
            }
            Style pageStyle = this.findStyle(inPage, nodeType);
            style.fillNullFields(pageStyle);
            return style;
        }

        private Style findStyle(Section node, NodeType nodeTypeToFind) {
            return this.getStyle(nodeTypeToFind, node.getSpecifications());
        }

        private Style findStyle(Page node, NodeType nodeTypeToFind) {
            return this.getStyle(nodeTypeToFind, node.getSpecificationList());
        }

        private Style getStyle(NodeType nodeTypeToFind, Iterable<Specification> specifications) {
            Style returnStyle = new Style();
            EvaluateDefaultStyle fetcher = new EvaluateDefaultStyle();
            for (Specification specification : specifications) {
                Style elementStyle = fetcher.findStyle(specification, nodeTypeToFind);
                returnStyle.fillNullFields(elementStyle);
            }
            return returnStyle;
        }
    }
}
