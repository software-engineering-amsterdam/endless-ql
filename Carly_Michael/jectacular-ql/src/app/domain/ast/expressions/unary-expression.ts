import {ExpressionType} from './expression-type';
import {Expression, LiteralType, UnaryOperator} from './expression';
import {Location} from '../location';
import {UnknownOperatorError, UnsupportedTypeError} from '../../errors';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';

export class UnaryExpression extends Expression {
  constructor(public right: Expression, public operator: UnaryOperator, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return this.right.getVariables();
  }

  checkType(allQuestions: Question[]): ExpressionType {
    if (this.operator === '-' && this.right.checkType(allQuestions) === ExpressionType.NUMBER) {
      return ExpressionType.NUMBER;
    } else if (this.operator === '!' && this.right.checkType(allQuestions) === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `Type of expression does not match operator ${this.operator}`
        + this.getLocationErrorMessage()
      );
    }
  }

  evaluate(form: FormGroup): LiteralType {
    switch (this.operator) {
      case '-': return - this.right.evaluate(form);
      case '!': return ! this.right.evaluate(form);
      default: throw new UnknownOperatorError(`Unknown operator ${this.operator}` +
      this.getLocationErrorMessage());
    }
  }
}
