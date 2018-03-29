package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.interpreter.widget.WidgetType;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.HashMap;
import java.util.Map;

public class TypeCheck extends BaseStyleASTVisitor<Void> implements IQLSStaticAnalysis {

    private final Messages message = new Messages();

    private Map<String, NodeType> qlQuestionNodeTypes = new HashMap<>();


    private TypeCheck() {

    }

    private void errorNotCompatible(Question node, WidgetType widgetType, NodeType nodeType) {
        this.message.addMessage(widgetType + " is not compatible with " + nodeType + " on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    private void error(Question node, String errorMessage) {
        this.message.addMessage(errorMessage + " on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    @Override
    public Messages doCheck(Form form, Stylesheet stylesheet) {
        this.qlQuestionNodeTypes = this.getQLQuestionNodeTypes(form);

        //Will check QLS questions with the QL types and reports messages inside this.message
        stylesheet.accept(this);

        return this.message;
    }

    private Map<String, NodeType> getQLQuestionNodeTypes(Form form) {
        Map<String, NodeType> questionTypes = new HashMap<>();
        form.accept(new BaseASTVisitor<Void>() {
            @Override
            public Void visit(org.uva.sea.languages.ql.parser.elements.Question node) {
                questionTypes.put(node.getVariable().getVariableName(), node.getNodeType().getNodeType());
                return super.visit(node);
            }
        });
        return questionTypes;
    }

    @Override
    public Void visit(Question node) {
        WidgetType widgetType;
        if (node.getWidget() == null)
            return null;

        widgetType = node.getWidget().getWidgetType();
        NodeType questionNodeType = this.qlQuestionNodeTypes.get(node.getName());
        if (questionNodeType == null) {
            this.error(node, node.getName() + " has no type");
            return null;
        }

        if (!widgetType.isCompatible(questionNodeType)) {
            this.errorNotCompatible(node, widgetType, questionNodeType);
            return null;
        }

        return null;
    }


    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public Messages doCheck(Form form, Stylesheet stylesheet) {
            IQLSStaticAnalysis checker = new TypeCheck();
            return checker.doCheck(form, stylesheet);
        }
    }
}
