import {ExpressionType} from './expression-type';
import {Expression, LiteralType} from './expression';
import {Location} from '../../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export abstract class LogicalExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  checkType(allQuestions: Question[]): ExpressionType {
    if (this.left.checkType(allQuestions) === ExpressionType.BOOLEAN &&
      this.right.checkType(allQuestions) === ExpressionType.BOOLEAN) {
      return ExpressionType.BOOLEAN;
    } else {
      throw new TypeError(
        `The logical expression can only compare boolean expressions`
        + this.getLocationErrorMessage()
      );
    }
  }

  abstract evaluate(form: FormGroup): LiteralType;
}

export class AndExpression extends LogicalExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) && this.right.evaluate(form);
  }
}

export class OrExpression extends LogicalExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) || this.right.evaluate(form);
  }
}
