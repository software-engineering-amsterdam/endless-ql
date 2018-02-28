import {ExpressionType} from './expression-type';
import {ArithmeticOperator, Expression, LiteralType} from './expression';
import {Location} from '../location';
import {UnknownOperatorError} from '../../errors';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export class ArithmeticExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, operator: ArithmeticOperator, location: Location) {
    super(left, right, operator, location);
  }

  checkType(allQuestions: Question[]): ExpressionType {
    const typeLeft = this.left.checkType(allQuestions);
    const typeRight = this.right.checkType(allQuestions);

    if (typeLeft === typeRight &&
        typeLeft === ExpressionType.NUMBER) {
      return typeLeft;
    } else {
      throw new TypeError(
        `Type of expression left(${typeLeft}) is different from type of expression right (${typeRight})`
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(form: FormGroup): LiteralType {
    switch (this.operator) {
      case '*': return <number>this.left.evaluate(form) * <number>this.right.evaluate(form);
      case '/': return <number>this.left.evaluate(form) / <number>this.right.evaluate(form);
      case '+': return <number>this.left.evaluate(form) + <number>this.right.evaluate(form);
      case '-': return <number>this.left.evaluate(form) - <number>this.right.evaluate(form);
      default: throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
      this.getLocationErrorMessage());
    }
  }
}
