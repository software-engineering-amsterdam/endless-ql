import {ExpressionType} from './expression-type';
import {Expression, LiteralType, UnaryOperator} from './expression';
import {Location} from './location';
import {UnknownOperatorError, UnsupportedTypeError} from '../errors';

export class UnaryExpression extends Expression {
  constructor(public right: Expression, public operator: UnaryOperator, location: Location) {
    super(location);
  }

  checkType(): ExpressionType {
    if (this.operator === '-' && this.right.checkType() === ExpressionType.NUMBER) {
      return ExpressionType.NUMBER;
    } else if (this.operator === '!' && this.right.checkType() === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `Type of expression does not match operator ${this.operator}`
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(): LiteralType {
    switch (this.operator) {
      case '-': return - this.right.evaluate();
      case '!': return ! this.right.evaluate();
      default: throw new UnknownOperatorError(`Unknown operator ${this.operator}` +
      this.getLocationErrorMessage());
    }
  }
}
