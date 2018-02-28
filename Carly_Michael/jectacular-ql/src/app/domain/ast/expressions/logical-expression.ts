import {ExpressionType} from './expression-type';
import {Expression, LiteralType, LogicalOperator} from './expression';
import {Location} from '../location';
import {UnknownOperatorError} from '../../errors';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';
import {BinaryExpression} from './binary-expression';

export class LogicalExpression extends BinaryExpression {
  constructor(left: Expression, right: Expression, operator: LogicalOperator, location: Location) {
    super(left, right, operator, location);
  }

  getVariables(): Variable[] {
    return undefined;
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

  evaluate(form: FormGroup): LiteralType {
    switch (this.operator) {
      case '&&': return this.left.evaluate(form) && this.right.evaluate(form);
      case '||': return this.left.evaluate(form) || this.right.evaluate(form);
      default: throw new UnknownOperatorError(`Unknown operator ${this.operator} ` +
      this.getLocationErrorMessage());
    }
  }
}
