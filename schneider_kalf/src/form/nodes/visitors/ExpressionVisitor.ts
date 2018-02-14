import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Multiplication from "../expressions/arithmetic/Multiplication";
import Or from "../expressions/boolean_expressions/Or";
import And from "../expressions/boolean_expressions/And";
import Negation from "../expressions/boolean_expressions/Negation";
import Variable from "../expressions/Variable";
import Division from "../expressions/arithmetic/Division";
import BooleanLiteral from "../expressions/boolean_expressions/BooleanLiteral";
import Subtraction from "../expressions/arithmetic/Subtraction";
import Equals from "../expressions/comparisons/Equals";

interface ExpressionVisitor {
  visitAddition(addition: Addition): any;

  visitNumberLiteral(literal: NumberLiteral): any;

  visitMultiplication(multiplication: Multiplication): any;

  visitOr(or: Or): any;

  visitAnd(and: And): any;

  visitNegation(negation: Negation): any;

  visitVariable(variable: Variable): any;

  visitDivision(division: Division): any;

  visitBooleanLiteral(literal: BooleanLiteral): any;

  visitSubtraction(subtraction: Subtraction): any;

  visitEquals(equals: Equals): any;
}

export default ExpressionVisitor;