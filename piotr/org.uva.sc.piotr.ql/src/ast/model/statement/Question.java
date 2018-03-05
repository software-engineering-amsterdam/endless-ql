package ast.model.statement;

import ast.model.datatypes.TypeDeclaration;
import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class Question implements Statement {

    private String label;
    private String variableName;
    private TypeDeclaration variableType;
    private Expression assignedExpression;

    public Question(String label, String variableName, TypeDeclaration variableType) {
        this.label = label;
        this.variableName = variableName;
        this.variableType = variableType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public TypeDeclaration getVariableType() {
        return variableType;
    }

    public void setVariableType(TypeDeclaration variableType) {
        this.variableType = variableType;
    }

    public Expression getAssignedExpression() {
        return assignedExpression;
    }

    public void setAssignedExpression(Expression assignedExpression) {
        this.assignedExpression = assignedExpression;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void debugPrint() {
        System.out.println("Question: {\n" +
                "  label:" + this.label +
                "}");
    }
}
