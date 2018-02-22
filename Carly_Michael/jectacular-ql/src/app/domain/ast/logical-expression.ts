import {ExpressionType} from './expression-type';
import {Expression, LiteralType, LogicalOperator} from './expression';
import {Location} from './location';
import {UnknownOperatorError} from '../errors';

export class LogicalExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: LogicalOperator, location: Location) {
    super(location);
  }

  checkType(): ExpressionType {
    if (this.left.checkType() === ExpressionType.BOOLEAN && this.right.checkType() === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `The logical expression can only compare boolean expressions`
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(): LiteralType {
    switch (this.operator) {
      case '&&': return this.left.evaluate() && this.right.evaluate();
      case '||': return this.left.evaluate() || this.right.evaluate();
      default: throw new UnknownOperatorError(`Unknown operator ${this.operator} ` +
      this.getLocationErrorMessage());
    }
  }
}
