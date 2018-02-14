import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Multiplication from "../expressions/arithmetic/Multiplication";
import ExpressionVisitor from "./ExpressionVisitor";
import Expression from "../expressions/Expression";

export class EvaluationVisitor implements ExpressionVisitor {
  visitMultiplication(multiplication: Multiplication): any {
    return multiplication.left.accept(this) * multiplication.right.accept(this);
  }

  visitAddition(addition: Addition): any {
    return addition.left.accept(this) + addition.right.accept(this);
  }

  visitLiteral(literal: NumberLiteral): any {
    return literal.getValue();
  }

  visitExpression(expression: Expression) {
    throw new Error("Method not implemented.");
  }
}