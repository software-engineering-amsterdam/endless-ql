package loader.QL;

import antlr.ql.FormBaseListener;
import antlr.ql.FormParser;
import domain.model.ast.FormNode;
import domain.model.ast.Condition;
import domain.model.ast.ConditionNode;
import domain.model.ast.QuestionNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.BooleanExpressionValue;
import domain.model.variable.*;
import exception.DuplicateQuestionDeclarationException;
import exception.InvalidConditionException;
import exception.ReferenceUndefinedVariableException;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class QLLoader extends FormBaseListener {
    private FormNode formNode;

    private QLChecker qlChecker;
    private Variable constructedVariable;
    private ConditionNode conditionNode;
    private Condition condition;

    private boolean inConditionNode = false;
    private boolean inElseNode = false;
    private Set<LoaderErrorListener> listeners = new HashSet<>();

    public QLLoader(){
        this.formNode = new FormNode();
    }

    @Override
    public void enterFormBuilder(FormParser.FormBuilderContext ctx) {
        this.formNode.setFormIdentifier(ctx.CHARACTERS().getText());
    }
    @Override
    public void exitFormData(FormParser.FormDataContext ctx){
        this.qlChecker = new QLChecker(formNode);
        try {
            this.qlChecker.doChecks();
        } catch (ReferenceUndefinedVariableException | DuplicateQuestionDeclarationException | InvalidConditionException e) {
            this.notifyListenersWithError(e.getMessage());
            return;
        }
    }

    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        conditionNode = new ConditionNode(false);
        this.inConditionNode = true;
        this.formNode.addConditionNode(conditionNode);
    }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.inConditionNode = false;
    }

    @Override
    public void enterElseStructure(FormParser.ElseStructureContext ctx){
        this.inElseNode = true;
    }

    @Override
    public void exitElseStructure(FormParser.ElseStructureContext ctx){
        this.inElseNode = false;
    }

    @Override
    public void enterCondition(FormParser.ConditionContext ctx){
        Variable variable = null;
        if (ctx.value() instanceof FormParser.ValueContext){
            variable = this.formNode.getVariableFromList(ctx.value().getText());
            this.formNode.getReferencedVariables().add(variable);
        }
        if (ctx.expression() instanceof FormParser.ExpressionContext){
            variable = new BooleanVariable(null, false);
            variable.setValue(this.getBooleanExpressionValue(ctx.expression().booleanExpression()));
        }
        condition = new Condition(variable);
        conditionNode.addCondition(condition);
    }
    @Override
    public void enterBooleanOperator(FormParser.BooleanOperatorContext ctx){
        condition.setOperator(ctx.getText());
    }
    @Override
    public void enterQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx) {
        constructedVariable = null;
        switch(ctx.variableType().getText()) {
            case "money":
                constructedVariable = new MoneyVariable(ctx.variable().getText(), 0);
                break;
            case "boolean":
                constructedVariable = new BooleanVariable(ctx.variable().getText(), false);
                break;
            case "string":
                constructedVariable = new StringVariable(ctx.variable().getText(), "");
                break;
            default:
                this.notifyListenersWithError("Unknown variable type used");
                //TODO Invalid variable type found. throw exception
        }
    }
    @Override
    public void exitQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx) {
        QuestionNode q = new QuestionNode(ctx.label().getText(), constructedVariable, this.inConditionNode);
        if(this.inConditionNode) {
            if (this.inElseNode){
                this.conditionNode.getElseNode().addQuestion(q);
                return;
            }
            this.conditionNode.addQuestion(q);
            return;
        }else{
            this.formNode.addQuestion(new QuestionNode(ctx.label().getText(), constructedVariable, false));
        }
    }
    @Override
    public void enterVariableValue(FormParser.VariableValueContext ctx){
        if(ctx.expression().arithmeticExpression() instanceof FormParser.ArithmeticExpressionContext){
            constructedVariable.setValue(getArithmeticExpressionValue(ctx.expression().arithmeticExpression()));
        }else if(ctx.expression().booleanExpression() instanceof FormParser.BooleanExpressionContext){
            constructedVariable.setValue(getBooleanExpressionValue(ctx.expression().booleanExpression()));
        }
    }
    public FormNode getFormNode() {
        return formNode;
    }

    private BooleanExpressionValue getBooleanExpressionValue(FormParser.BooleanExpressionContext bec){
        Variable left = this.formNode.getVariableFromList(bec.variable(0).getText());
        Variable right = this.formNode.getVariableFromList(bec.variable(1).getText());
        String operator = bec.booleanExpressionOperator().getText();
        this.formNode.getReferencedVariables().add(left);
        this.formNode.getReferencedVariables().add(right);
        return (new BooleanExpressionValue(left, right, operator));
    }

    private ArithmeticExpressionValue getArithmeticExpressionValue(FormParser.ArithmeticExpressionContext aec){
        Variable left = this.formNode.getVariableFromList(aec.variable(0).getText());
        Variable right = this.formNode.getVariableFromList(aec.variable(1).getText());
        String operator = aec.arithmeticExpressionOperator().getText();
        this.formNode.getReferencedVariables().add(left);
        this.formNode.getReferencedVariables().add(right);
        return (new ArithmeticExpressionValue(left, right, operator));
    }

    public void addErrorListener(LoaderErrorListener listener) {
        listeners.add(listener);
    }

    private void notifyListenersWithError(String message) {
        this.listeners.forEach(l -> l.onError(message));
    }

}
