import {ExpressionType} from './expression-type';
import {Expression} from './expression';
import {BinaryOperator} from './expression';
import {Location} from './location';

export class BinaryExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: BinaryOperator, location: Location) {
    super(location);
  }

  getType(): ExpressionType {
    if (this.left.getType() === this.right.getType()) {
      return this.left.getType();
    } else {
      throw new TypeError(
        `Type of expression left is different from type of expression right `
        + this.getLocationErrorMessage()
      );
    }
  }
}
