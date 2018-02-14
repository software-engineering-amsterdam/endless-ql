import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Multiplication from "../expressions/arithmetic/Multiplication";
import ExpressionVisitor from "./ExpressionVisitor";
import Expression from "../expressions/Expression";
import Negation from "../expressions/boolean_expressions/Negation";
import And from "../expressions/boolean_expressions/And";
import Or from "../expressions/boolean_expressions/Or";
import { assertBoolean, assertNumeric } from "../../typechecking/typeAssertions";
import Variable from "../expressions/Variable";
import { NotImplementedYetError } from "../../errors";

export class EvaluationVisitor implements ExpressionVisitor {
  visitVariable(variable: Variable) {
    throw NotImplementedYetError.make("Evaluate variables");
  }

  visitNegation(negation: Negation): any {
    return assertBoolean(negation.expression.accept(this)) === false;
  }

  visitAnd(and: And): any {
    return assertBoolean(and.left.accept(this)) && assertBoolean(and.right.accept(this));
  }

  visitOr(or: Or): any {
    return assertBoolean(or.left.accept(this)) || assertBoolean(or.right.accept(this));
  }

  visitMultiplication(multiplication: Multiplication): any {
    return assertNumeric(multiplication.left.accept(this)) * assertNumeric(multiplication.right.accept(this));
  }

  visitAddition(addition: Addition): any {
    return assertNumeric(addition.left.accept(this)) + assertNumeric(addition.right.accept(this));
  }

  visitLiteral(literal: NumberLiteral): any {
    return literal.getValue();
  }

  visitExpression(expression: Expression) {
    throw new Error("Method not implemented.");
  }
}