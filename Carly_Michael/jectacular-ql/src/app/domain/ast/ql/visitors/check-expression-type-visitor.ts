import {ExpressionVisitor} from './expression-visitor';
import {
  AddExpression, AndExpression, BooleanLiteral, DateLiteral, DivideExpression, EqualExpression,
  GreaterThanEqualExpression,
  GreaterThanExpression,
  LessThanEqualExpression,
  LessThanExpression,
  MultiplyExpression, NegateExpression, NegativeExpression,
  NumberLiteral, OrExpression,
  StringLiteral, SubtractExpression, UnequalExpression, Variable
} from '../';
import {ExpressionType, ExpressionTypeUtil} from '../expressions/expression-type';
import {Location, locationToReadableMessage} from '../../location';
import {Expression} from '../index';

export class CheckExpressionTypeVisitor implements ExpressionVisitor<ExpressionType> {
  private constructor() { }

  static evaluate(expr: Expression): ExpressionType {
    const visitor = new CheckExpressionTypeVisitor();
    return expr.accept(visitor);
  }

  visitBooleanLiteral(literal: BooleanLiteral): ExpressionType {
    return literal.getType();
  }

  visitNumberLiteral(literal: NumberLiteral): ExpressionType {
    return literal.getType();
  }

  visitStringLiteral(literal: StringLiteral): ExpressionType {
    return literal.getType();
  }

  visitDateLiteral(literal: DateLiteral): ExpressionType {
    return literal.getType();
  }

  visitMultiplyExpression(expr: MultiplyExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitDivideExpression(expr: DivideExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitAddExpression(expr: AddExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitSubtractExpression(expr: SubtractExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitGreaterThanExpression(expr: GreaterThanExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitGreaterThanEqualExpression(expr: GreaterThanEqualExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitLessThanExpression(expr: LessThanExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitLessThanEqualExpression(expr: LessThanEqualExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.NUMBER;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitEqualExpression(expr: EqualExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitUnequalExpression(expr: UnequalExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight;
    }, typeLeft, typeRight, expr.location);

    return ExpressionType.BOOLEAN;
  }

  visitAndExpression(expr: AndExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.BOOLEAN;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitOrExpression(expr: OrExpression): ExpressionType {
    const typeLeft = expr.left.accept(this);
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowBinary(() => {
      return typeLeft === typeRight && typeLeft === ExpressionType.BOOLEAN;
    }, typeLeft, typeRight, expr.location);

    return typeLeft;
  }

  visitNegativeExpression(expr: NegativeExpression): ExpressionType {
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowUnary(() => {
      return typeRight === ExpressionType.NUMBER;
    }, typeRight, expr.location);

    return typeRight;
  }

  visitNegateExpression(expr: NegateExpression): ExpressionType {
    const typeRight = expr.right.accept(this);

    this.checkConditionOrThrowUnary(() => {
      return typeRight === ExpressionType.BOOLEAN;
    }, typeRight, expr.location);

    return typeRight;
  }

  visitVariable(expr: Variable): ExpressionType {
    return expr.getExpressionType();
  }

  private checkConditionOrThrowBinary(condition: () => boolean, typeLeft: ExpressionType, typeRight: ExpressionType, location: Location) {
    if (!condition()) {
      throw new TypeError(
        `Type of expression left(${ExpressionTypeUtil.toString(typeLeft)}) is` +
        `different from type of expression right (${ExpressionTypeUtil.toString(typeRight)})`
        + locationToReadableMessage(location)
      );
    }
  }

  private checkConditionOrThrowUnary(condition: () => boolean, typeRight: ExpressionType, location: Location) {
    if (!condition()) {
      throw new TypeError(
        `Type of expression (${ExpressionTypeUtil.toString(typeRight)}) does not match operator`
        + locationToReadableMessage(location)
      );
    }
  }
}
