import Addition from "../expressions/arithmetic/Addition";
import NumberLiteral from "../expressions/arithmetic/NumberLiteral";
import Multiplication from "../expressions/arithmetic/Multiplication";
import ExpressionVisitor from "./ExpressionVisitor";
import Negation from "../expressions/boolean_expressions/Negation";
import And from "../expressions/boolean_expressions/And";
import Or from "../expressions/boolean_expressions/Or";
import {
  assertBoolean, assertComparable, assertNumeric, assertSameType,
  assertValidDivision
} from "../../typechecking/typeAssertions";
import Variable from "../expressions/Variable";
import { NotImplementedYetError } from "../../errors";
import BooleanLiteral from "../expressions/boolean_expressions/BooleanLiteral";
import Division from "../expressions/arithmetic/Division";
import Subtraction from "../expressions/arithmetic/Subtraction";
import Equals from "../expressions/comparisons/Equals";

/**
 * TODO: Maybe use mixins to seperate boolean and arithmetic logic
 */
export default class EvaluationVisitor implements ExpressionVisitor {
  visitEquals(equals: Equals) {
    const leftValue: any = equals.left.accept(this);
    const rightValue: any = equals.right.accept(this);

    assertSameType(leftValue, rightValue);
    assertComparable(leftValue);

    return leftValue === rightValue;
  }

  visitVariable(variable: Variable) {
    throw NotImplementedYetError.make("Evaluate variables");
  }

  visitNegation(negation: Negation): any {
    return assertBoolean(negation.expression.accept(this)) === false;
  }

  visitBooleanLiteral(literal: BooleanLiteral): any {
    return assertBoolean(literal.getValue());
  }

  visitDivision(division: Division): any {
    const dividendValue: any = division.dividend.accept(this);
    const divisorValue: any = division.divisor.accept(this);
    assertValidDivision(dividendValue, divisorValue);

    return dividendValue.accept(this) / divisorValue.accept(this);
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

  visitSubtraction(subtraction: Subtraction): any {
    return assertNumeric(subtraction.left.accept(this)) - assertNumeric(subtraction.right.accept(this));
  }

  visitNumberLiteral(literal: NumberLiteral): any {
    return assertNumeric(literal.getValue());
  }
}