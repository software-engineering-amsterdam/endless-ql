import {ExpressionType} from './expression-type';
import {Expression, LogicalOperator} from './expression';
import {Location} from './location';

export class LogicalExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: LogicalOperator, location: Location) {
    super(location);
  }

  getType(): ExpressionType {
    if (this.left.getType() === ExpressionType.BOOLEAN && this.right.getType() === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `The logical expression can only compare boolean expressions`
        + this.getLocationErrorMessage()
      );
    }
  }
}
