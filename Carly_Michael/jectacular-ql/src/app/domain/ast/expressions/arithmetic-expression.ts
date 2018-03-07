import {ExpressionType} from './expression-type';
import {Expression, LiteralType} from './expression';
import {Location} from '../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export abstract class ArithmeticExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
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

  abstract evaluate(form: FormGroup): LiteralType;
}

export class MultiplyExpression extends ArithmeticExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return <number>this.left.evaluate(form) * <number>this.right.evaluate(form);
  }
}

export class DivideExpression extends ArithmeticExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return <number>this.left.evaluate(form) / <number>this.right.evaluate(form);
  }
}

export class AddExpression extends ArithmeticExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return <number>this.left.evaluate(form) + <number>this.right.evaluate(form);
  }
}

export class SubtractExpression extends ArithmeticExpression {
  constructor(left: Expression, right: Expression, location: Location) {
    super(left, right, location);
  }

  evaluate(form: FormGroup): LiteralType {
    return <number>this.left.evaluate(form) - <number>this.right.evaluate(form);
  }
}
