import {ExpressionVisitor} from './expression-visitor';
import {
  AddExpression, AndExpression,
  BooleanLiteral, DateLiteral, DivideExpression, EqualExpression, Expression, GreaterThanEqualExpression,
  GreaterThanExpression,
  LessThanEqualExpression,
  LessThanExpression,
  MultiplyExpression, NegateExpression, NegativeExpression, NumberLiteral, OrExpression,
  StringLiteral, SubtractExpression, UnequalExpression, Variable
} from '../index';

export class CollectExpressionVariablesVisitor implements ExpressionVisitor<void> {
  private variables: Variable[];
  private constructor() {
    this.variables = [];
  }

  static evaluate(expr: Expression): ReadonlyArray<Variable> {
    const visitor = new CollectExpressionVariablesVisitor();
    expr.accept(visitor);
    return visitor.variables;
  }

  visitBooleanLiteral(literal: BooleanLiteral): void {
    // nothing to do
  }

  visitNumberLiteral(literal: NumberLiteral): void {
    // nothing to do
  }

  visitStringLiteral(literal: StringLiteral): void {
    // nothing to do
  }

  visitDateLiteral(literal: DateLiteral): void {
    // nothing to do
  }

  visitMultiplyExpression(expr: MultiplyExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitDivideExpression(expr: DivideExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitAddExpression(expr: AddExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitSubtractExpression(expr: SubtractExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitGreaterThanExpression(expr: GreaterThanExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitGreaterThanEqualExpression(expr: GreaterThanEqualExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitLessThanExpression(expr: LessThanExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitLessThanEqualExpression(expr: LessThanEqualExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitEqualExpression(expr: EqualExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitUnequalExpression(expr: UnequalExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitAndExpression(expr: AndExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitOrExpression(expr: OrExpression): void {
    expr.left.accept(this);
    expr.right.accept(this);
  }

  visitNegativeExpression(expr: NegativeExpression): void {
    expr.right.accept(this);
  }

  visitNegateExpression(expr: NegateExpression): void {
    expr.right.accept(this);
  }

  visitVariable(expr: Variable): void {
    this.variables.push(expr);
  }

}
