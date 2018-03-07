import {ExpressionType} from './expression-type';
import {ComparisonOperator, Expression, LiteralType} from './expression';
import {Location} from '../location';
import {UnknownOperatorError} from '../../errors';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {BinaryExpression} from './binary-expression';

export class ComparisonExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, private operator: ComparisonOperator, location: Location) {
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

  evaluate(form: FormGroup): LiteralType {
    switch (this.operator) {
      case '>':
        return <number>this.left.evaluate(form) > <number>this.right.evaluate(form);
      case '<':
        return <number>this.left.evaluate(form) < <number>this.right.evaluate(form);
      case '>=':
        return <number>this.left.evaluate(form) >= <number>this.right.evaluate(form);
      case '<=':
        return <number>this.left.evaluate(form) <= <number>this.right.evaluate(form);
      default:
        throw new UnknownOperatorError(`Operator ${this.operator} is unknown` +
          this.getLocationErrorMessage());
    }
  }
}
