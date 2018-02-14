import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Expression from "../expressions/Expression";
import Multiplication from "../expressions/arithmetic/Multiplication";

interface ExpressionVisitor {
  visitAddition(addition: Addition): any;

  visitLiteral(literal: NumberLiteral): any;

  visitExpression(expression: Expression): any;

  visitMultiplication(multiplication: Multiplication): any;
}

export default ExpressionVisitor;