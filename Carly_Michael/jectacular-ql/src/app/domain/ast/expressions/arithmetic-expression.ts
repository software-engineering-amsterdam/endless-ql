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
    if (this.left.checkType(allQuestions) === this.right.checkType(allQuestions) &&
        this.left.checkType(allQuestions) === ExpressionType.NUMBER) {
      return this.left.checkType(allQuestions);
    } else {
      throw new TypeError(
        `Type of expression left is different from type of expression right `
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
