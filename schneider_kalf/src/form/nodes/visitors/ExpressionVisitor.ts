import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Expression from "../expressions/Expression";
import Multiplication from "../expressions/arithmetic/Multiplication";
import Or from "../expressions/boolean_expressions/Or";
import And from "../expressions/boolean_expressions/And";
import Negation from "../expressions/boolean_expressions/Negation";
import Variable from "../expressions/Variable";
import Division from "../expressions/arithmetic/Division";

interface ExpressionVisitor {
  visitAddition(addition: Addition): any;

  visitLiteral(literal: NumberLiteral): any;

  visitExpression(expression: Expression): any;

  visitMultiplication(multiplication: Multiplication): any;

  visitOr(or: Or): any;

  visitAnd(and: And): any;

  visitNegation(negation: Negation): any;

  visitVariable(variable: Variable): any;

  visitDivision(division: Division): any;
}

export default ExpressionVisitor;