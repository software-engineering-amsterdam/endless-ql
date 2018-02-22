import {ExpressionType} from './expression-type';
import {ComparisonOperator, Expression, LiteralType} from './expression';
import {Location} from './location';
import {UnknownOperatorError} from '../errors';

export class ComparisonExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: ComparisonOperator, location: Location) {
    super(location);
  }

  checkType(): ExpressionType {
    if (this.left.checkType() === this.right.checkType() && this.left.checkType() === ExpressionType.NUMBER) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `Type of expression left is different from type of expression right `
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(): LiteralType {
    switch (this.operator) {
      case '>': return <number>this.left.evaluate() > <number>this.right.evaluate();
      case '<': return <number>this.left.evaluate() < <number>this.right.evaluate();
      case '>=': return <number>this.left.evaluate() >= <number>this.right.evaluate();
      case '<=': return <number>this.left.evaluate() <= <number>this.right.evaluate();
      default: throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
        this.getLocationErrorMessage());
    }
  }
}
