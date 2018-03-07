import {ExpressionType} from './expression-type';
import {Expression, LiteralType} from './expression';
import {Location} from '../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export abstract class ComparisonExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  checkType(allQuestions: Question[]): ExpressionType {
    if (this.left.checkType(allQuestions) === this.right.checkType(allQuestions) &&
      this.left.checkType(allQuestions) === ExpressionType.NUMBER) {
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

export class GreaterThanExpression extends ComparisonExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) > this.right.evaluate(form);
  }
}

export class GreaterThanEqualExpression extends ComparisonExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) >= this.right.evaluate(form);
  }
}

export class LessThanExpression extends ComparisonExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) < this.right.evaluate(form);
  }
}

export class LessThanEqualExpression extends ComparisonExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return this.left.evaluate(form) <= this.right.evaluate(form);
  }
}


