package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.interpreter.evaluate.EvaluateDefaultStyle.Fetcher;
import org.uva.sea.languages.qls.parser.elements.Page;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.elements.specification.Section;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TypeCheck extends BaseStyleASTVisitor<Void> implements IQLSStaticAnalysis {

    private final Messages message = new Messages();

    private final Fetcher defaultStyleEvaluator = new Fetcher();

    //Current state for visitor Visitor
    private Page currentPage;

    private final Stack<Section> currentSections = new Stack<>();

    private Map<String, NodeType> qlQuestionNodeTypes;


    /**
     * Hide constructor
     */
    private TypeCheck() {

    }

    /**
     * Display errorNotCompatible message
     *
     * @param node
     * @param widgetType
     * @param nodeType
     */
    private void errorNotCompatible(Question node, WidgetType widgetType, NodeType nodeType) {
        this.message.addMessage(widgetType + " is not compatible with " + nodeType + " on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    private void error(Question node, String errorMessage) {
        this.message.addMessage(errorMessage + " on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }


    /**
     * @param form
     * @param stylesheet
     * @return
     */
    @Override
    public Messages doCheck(Form form, Stylesheet stylesheet) {
        this.qlQuestionNodeTypes = this.getQLQuestionNodeTypes(form);

        //Will check QLS questions with the QL types
        stylesheet.accept(this);
        return this.message;
    }

    /**
     * Get all QL question names
     *
     * @param form AST node
     * @return The names
     */
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
    public Void visit(Page node) {
        this.currentPage = node;
        return super.visit(node);
    }

    @Override
    public Void visit(Section node) {
        this.currentSections.add(node);
        super.visit(node);
        this.currentSections.pop();
        return null;
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


    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public Messages doCheck(Form form, Stylesheet stylesheet) {
            IQLSStaticAnalysis checker = new TypeCheck();
            return checker.doCheck(form, stylesheet);
        }
    }
}