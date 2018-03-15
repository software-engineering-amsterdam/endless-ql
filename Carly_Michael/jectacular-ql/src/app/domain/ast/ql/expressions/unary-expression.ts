import {ExpressionType} from './expression-type';
import {Expression, LiteralType} from './expression';
import {Location} from '../../location';
import {QlQuestion} from '../ql-question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';

export abstract class UnaryExpression extends Expression {
  constructor(public right: Expression, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return this.right.getVariables();
  }

  abstract checkType(allQuestions: QlQuestion[]): ExpressionType;

  abstract evaluate(form: FormGroup): LiteralType;
}

export class NegativeExpression extends UnaryExpression {
  constructor(right: Expression, location: Location) {
    super(right, location);
  }

  checkType(allQuestions: QlQuestion[]): ExpressionType {
    if (this.right.checkType(allQuestions) === ExpressionType.NUMBER) {
      return ExpressionType.NUMBER;
    }

    throw new TypeError(
      `Type of expression does not match operator - (negative)`
      + this.getLocationErrorMessage()
    );
  }

  evaluate(form: FormGroup): LiteralType {
    return - this.right.evaluate(form);
  }
}

export class NegateExpression extends UnaryExpression {
  constructor(right: Expression, location: Location) {
    super(right, location);
  }

  checkType(allQuestions: QlQuestion[]): ExpressionType {
    if (this.right.checkType(allQuestions) === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    }

    throw new TypeError(
      `Type of expression does not match operator ! (negate)`
      + this.getLocationErrorMessage()
    );
  }

  evaluate(form: FormGroup): LiteralType {
    return ! this.right.evaluate(form);
  }
}
