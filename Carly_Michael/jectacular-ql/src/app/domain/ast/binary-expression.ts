import {ExpressionType} from './expression-type';
import {Expression, LiteralType} from './expression';
import {BinaryOperator} from './expression';
import {Location} from './location';
import {UnknownOperatorError} from '../errors';

export class BinaryExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: BinaryOperator, location: Location) {
    super(location);
  }

  checkType(): ExpressionType {
    if (this.left.checkType() === this.right.checkType()) {
      return this.left.checkType();
    } else {
      throw new TypeError(
        `Type of expression left is different from type of expression right `
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(): LiteralType {
    switch (this.operator) {
      case '*': return <number>this.left.evaluate() * <number>this.right.evaluate();
      case '/': return <number>this.left.evaluate() / <number>this.right.evaluate();
      case '+': return <number>this.left.evaluate() + <number>this.right.evaluate();
      case '-': return <number>this.left.evaluate() - <number>this.right.evaluate();
      default: throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
      this.getLocationErrorMessage());
    }
  }
}
