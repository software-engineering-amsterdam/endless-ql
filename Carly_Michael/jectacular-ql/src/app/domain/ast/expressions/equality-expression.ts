import {EqualityOperator, Expression, LiteralType} from './expression';
import {ExpressionType} from './expression-type';
import {Location} from '../location';
import {UnknownOperatorError} from '../../errors';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';
import {BinaryExpression} from './binary-expression';

export class EqualityExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, operator: EqualityOperator, location: Location) {
    super(left, right, operator, location);
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

  evaluate(form: FormGroup): LiteralType {
    switch (this.operator) {
      case '==': return this.left.evaluate(form) === this.right.evaluate(form);
      case '!=': return this.left.evaluate(form) !== this.right.evaluate(form);
      default: throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
        this.getLocationErrorMessage());
    }
  }
}
