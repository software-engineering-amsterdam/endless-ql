import ExpressionVisitor from "../nodes/visitors/ExpressionVisitor";
import Addition from "../nodes/expressions/arithmetic/Addition";
import NumberLiteral from "../nodes/expressions/arithmetic/NumberLiteral";
import Multiplication from "../nodes/expressions/arithmetic/Multiplication";
import Or from "../nodes/expressions/boolean_expressions/Or";
import And from "../nodes/expressions/boolean_expressions/And";
import Negation from "../nodes/expressions/boolean_expressions/Negation";
import VariableIdentifier from "../nodes/expressions/VariableIdentifier";
import Division from "../nodes/expressions/arithmetic/Division";
import BooleanLiteral from "../nodes/expressions/boolean_expressions/BooleanLiteral";
import Subtraction from "../nodes/expressions/arithmetic/Subtraction";
import Equals from "../nodes/expressions/comparisons/Equals";
import NotEqual from "../nodes/expressions/comparisons/NotEqual";
import LargerThan from "../nodes/expressions/comparisons/LargerThan";
import LargerThanOrEqual from "../nodes/expressions/comparisons/LargerThanOrEqual";
import SmallerThan from "../nodes/expressions/comparisons/SmallerThan";
import SmallerThanOrEqual from "../nodes/expressions/comparisons/SmallerThanOrEqual";
import StringLiteral from "../nodes/expressions/string/StringLiteral";

export class TypeCheckingVisitor implements ExpressionVisitor {
  visitAddition(addition: Addition): any {
    return undefined;
  }

  visitNumberLiteral(literal: NumberLiteral): any {
    return undefined;
  }

  visitMultiplication(multiplication: Multiplication): any {
    return undefined;
  }

  visitOr(or: Or): any {
    return undefined;
  }

  visitAnd(and: And): any {
    return undefined;
  }

  visitNegation(negation: Negation): any {
    return undefined;
  }

  visitVariableIdentifier(variable: VariableIdentifier): any {
    return undefined;
  }

  visitDivision(division: Division): any {
    return undefined;
  }

  visitBooleanLiteral(literal: BooleanLiteral): any {
    return undefined;
  }

  visitSubtraction(subtraction: Subtraction): any {
    return undefined;
  }

  visitEquals(equals: Equals): any {
    return undefined;
  }

  visitNotEqual(notEquals: NotEqual): any {
    return undefined;
  }

  visitLargerThan(largerThan: LargerThan): any {
    return undefined;
  }

  visitLargerThanOrEqual(largerThanOrEqual: LargerThanOrEqual): any {
    return undefined;
  }

  visitSmallerThan(smallerThan: SmallerThan): any {
    return undefined;
  }

  visitSmallerThanOrEqual(smallerThanOrEqual: SmallerThanOrEqual): any {
    return undefined;
  }

  visitStringLiteral(stringLiteral: StringLiteral): any {
    return undefined;
  }

}
