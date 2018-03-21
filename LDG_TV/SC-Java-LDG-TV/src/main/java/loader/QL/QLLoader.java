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


public class QLLoader extends FormBaseListener {
    private FormNode formNode;

    private QLChecker qlChecker;
    private Variable constructedVariable;
    private boolean inIfNode = false;
    private boolean inElseNode = false;

    public QLLoader(){
        this.formNode = new FormNode();
    }
    @Override
    public void enterFormBuilder(FormParser.FormBuilderContext ctx) {
        this.formNode.setFormIdentifier(ctx.CHARACTERS().getText());
    }
    @Override
    public void enterFormData(FormParser.FormDataContext ctx) {
    }
    @Override
    public void exitFormData(FormParser.FormDataContext ctx){
        this.qlChecker = new QLChecker(formNode);
        this.qlChecker.doChecks();
    }

    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        ConditionNode conditionNode = new ConditionNode(false);
        this.inIfNode = true;
        Variable v = null;
        Condition c = null;
        for(int i=0; i< ctx.statementBlockStructure().conditions().condition().size(); i++){
            FormParser.ConditionContext cc = ctx.statementBlockStructure().conditions().condition(i);
            FormParser.BooleanOperatorContext bo = ctx.statementBlockStructure().conditions().booleanOperator(i);
            if (cc.value() instanceof FormParser.ValueContext){
               v = this.formNode.getVariableFromList(cc.value().getText());
               this.formNode.getReferencedVariables().add(v);
            }
            if (cc.expression() instanceof FormParser.ExpressionContext){
               v = new BooleanVariable(null, false);
               v.setValue(this.getBooleanExpressionValue(cc.expression().booleanExpression()));
            }
            if(bo != null){
                c = new Condition(v, bo.getText());
            }else{
                c = new Condition(v, null);
            }
            conditionNode.addCondition(c);
        }
        this.formNode.addIfNode(conditionNode);

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
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.inIfNode = false;
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
                //TODO Invalid variable type found. throw exception
        }
    }
    @Override
    public void exitQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx) {
        String questionText = ctx.label().getText();

        QuestionNode q = new QuestionNode(questionText, constructedVariable, this.inIfNode);
        if(this.inIfNode) {
            if (this.inElseNode){
                this.formNode.addToLastIfElse(q);
                return;
            }
            this.formNode.addToLastIf(q);
            return;
        }

        this.formNode.addQuestion(new QuestionNode(questionText, constructedVariable, false));
    }
    @Override
    public void enterVariableValue(FormParser.VariableValueContext ctx){
        if(ctx.expression() instanceof FormParser.ExpressionContext){
            if(ctx.expression().arithmeticExpression() instanceof FormParser.ArithmeticExpressionContext){
                constructedVariable.setValue(getArithmeticExpressionValue(ctx.expression().arithmeticExpression()));
            }else if(ctx.expression().booleanExpression() instanceof FormParser.BooleanExpressionContext){
                constructedVariable.setValue(getBooleanExpressionValue(ctx.expression().booleanExpression()));
            }
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

}
