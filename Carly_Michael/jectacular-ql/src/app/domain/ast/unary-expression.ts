import {ExpressionType} from './expression-type';
import {Expression, UnaryOperator} from './expression';
import {Location} from './location';

export class UnaryExpression extends Expression {
  constructor(public right: Expression, public operator: UnaryOperator, location: Location) {
    super(location);
  }

  getType(): ExpressionType {
    if (this.operator === '-' && this.right.getType() === ExpressionType.NUMBER) {
      return ExpressionType.NUMBER;
    } else if (this.operator === '!' && this.right.getType() === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `Type of expression does not match operator ${this.operator}`
        + this.getLocationErrorMessage()
      );
    }
  }
}
