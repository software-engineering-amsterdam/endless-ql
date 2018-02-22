import {EqualityOperator, Expression, LiteralType} from './expression';
import {ExpressionType} from './expression-type';
import {Location} from './location';
import {UnknownOperatorError} from '../errors';
import {Question} from './question';

export class EqualityExpression extends Expression {
  constructor(public left: Expression, public right: Expression, public operator: EqualityOperator, location: Location) {
    super(location);
  }
  checkType(allQuestions: Question[]): ExpressionType {
    if (this.left.checkType(allQuestions) === this.right.checkType(allQuestions)) {
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
      case '==': return this.left.evaluate() === this.right.evaluate();
      case '!=': return this.left.evaluate() !== this.right.evaluate();
      default: throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
        this.getLocationErrorMessage());
    }
  }
}
