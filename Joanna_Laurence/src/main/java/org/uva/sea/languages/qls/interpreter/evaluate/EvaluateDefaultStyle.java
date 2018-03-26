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

public final class EvaluateDefaultStyle extends BaseStyleASTVisitor<Void> {

    private NodeType nodeTypeToFind = null;

    private Style foundStyle = new Style();


    private EvaluateDefaultStyle() {

    }

    private Style findStyle(final QLSNode node, final NodeType nodeTypeToFind) {
        this.nodeTypeToFind = nodeTypeToFind;
        node.accept(this);
        return this.foundStyle;
    }

    @Override
    public Void visit(final DefaultStyle style) {

        final NodeType styleType = NodeType.valueOf(style.getTypeName().toUpperCase());
        if (styleType != this.nodeTypeToFind)
            return null;

        final Style defaultStyle = new Style();
        style.accept(new BaseStyleASTVisitor<Void>() {
            @Override
            public Void visit(final Color node) {
                defaultStyle.setColor(node.getColorCode());
                return null;
            }

            @Override
            public Void visit(final Font node) {
                defaultStyle.setFont(node.getName());
                return null;
            }

            @Override
            public Void visit(final FontSize node) {
                defaultStyle.setFontSize(node.getSize());
                return null;
            }

            @Override
            public Void visit(final Widget node) {
                defaultStyle.setWidget(new QLWidget(node.getWidgetType(), node.getParametersAsStrings()));
                return null;
            }

            @Override
            public Void visit(final Width node) {
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
    public Void visit(final Question node) {
        return null;
    }

    @Override
    public Void visit(final Section node) {
        return null;
    }


    public static class Fetcher {
        public final Style getCascadingStyle(final NodeType nodeType, final List<Section> inSection, final Page inPage) {
            final Style style = new Style();

            final ListIterator<Section> li = inSection.listIterator(inSection.size());
            while (li.hasPrevious()) {
                final Style defaultStyle = this.findStyle(li.previous(), nodeType);
                style.fillNullFields(defaultStyle);
            }
            final Style pageStyle = this.findStyle(inPage, nodeType);
            style.fillNullFields(pageStyle);
            return style;
        }

        private Style findStyle(final Section node, final NodeType nodeTypeToFind) {
            return this.getStyle(nodeTypeToFind, node.getSpecifications());
        }

        private Style findStyle(final Page node, final NodeType nodeTypeToFind) {
            return this.getStyle(nodeTypeToFind, node.getSpecificationList());
        }

        private Style getStyle(final NodeType nodeTypeToFind, final Iterable<Specification> specifications) {
            final Style returnStyle = new Style();
            final EvaluateDefaultStyle fetcher = new EvaluateDefaultStyle();
            for (final Specification specification : specifications) {
                final Style elementStyle = fetcher.findStyle(specification, nodeTypeToFind);
                returnStyle.fillNullFields(elementStyle);
            }
            return returnStyle;
        }
    }
}
