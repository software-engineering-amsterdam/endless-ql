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
import { VariablesInformation, VariableInformation } from "../VariableIntformation";
import { NotComparableError, TypeCheckError, UnkownFieldError } from "../form_errors";
import { FieldType, getCommonNumericFieldType, isNumericFieldType } from "../FieldType";
import { assertBoolean, assertFieldType, assertNumericFieldType } from "./typeAssertions";
import BinaryOperator from "../nodes/expressions/BinaryOperator";

export class TypeCheckingVisitor implements ExpressionVisitor {
  private _variables: VariablesInformation;

  constructor(variables: VariablesInformation) {
    this._variables = variables;
  }

  visitAddition(addition: Addition): any {
    return this.visitNumericOperator(addition);
  }

  visitNumberLiteral(literal: NumberLiteral): any {
    if (Math.round(literal.getValue()) === literal.getValue()) {
      return FieldType.Integer;
    }

    return FieldType.Float;
  }

  visitMultiplication(multiplication: Multiplication): any {
    return this.visitNumericOperator(multiplication);
  }

  visitOr(or: Or): any {
    return this.visitBooleanOperator(or);
  }

  visitAnd(and: And): any {
    return this.visitBooleanOperator(and);
  }

  visitNegation(negation: Negation): any {
    return assertFieldType(negation.expression.accept(this), FieldType.Boolean);
  }

  visitVariableIdentifier(variable: VariableIdentifier): any {
    const variableInformation: VariableInformation | undefined = this._variables.get(variable.identifier);

    if (!variableInformation) {
      throw UnkownFieldError.make(variable.identifier);
    }

    return variableInformation.type;
  }

  visitDivision(division: Division): any {
    return this.visitNumericOperator(division);
  }

  visitBooleanLiteral(literal: BooleanLiteral): any {
    return FieldType.Boolean;
  }

  visitSubtraction(subtraction: Subtraction): any {
    return this.visitNumericOperator(subtraction);
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
    return FieldType.Text;
  }

  private visitBooleanOperator(operator: BinaryOperator): FieldType {
    assertFieldType(operator.left.accept(this), FieldType.Boolean);
    assertFieldType(operator.right.accept(this), FieldType.Boolean);
    return FieldType.Boolean;
  }

  private visitNumericOperator(operator: BinaryOperator): FieldType {
    const leftType = assertNumericFieldType(operator.left.accept(this));
    const rightType = assertNumericFieldType(operator.right.accept(this));

    return getCommonNumericFieldType(leftType, rightType);
  }

  private visitCompareOperator(operator: BinaryOperator) {
    const leftType = operator.left.accept(this);
    const rightType = operator.right.accept(this);

    if (isNumericFieldType(leftType) || isNumericFieldType(rightType)) {
      const commonType = getCommonNumericFieldType(leftType, rightType);

      if (!isNumericFieldType(commonType)) {
        throw NotComparableError.make(leftType.toString(), rightType.toString());
      }

      return FieldType.Boolean;
    }

    assertFieldType(leftType, rightType);

    return FieldType.Boolean;
  }
}
