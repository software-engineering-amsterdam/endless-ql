import {Expression, LiteralType} from './expression';
import {ExpressionType} from './expression-type';
import {Location} from '../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export abstract class EqualityExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
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

  abstract evaluate(form: FormGroup): LiteralType;
}

export class EqualExpression extends EqualityExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) === this.right.evaluate(form);
  }
}

export class InEqualExpression extends EqualityExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) !== this.right.evaluate(form);
  }
}
