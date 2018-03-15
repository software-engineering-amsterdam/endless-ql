import {ExpressionType} from './expression-type';
import {Location} from '../../location';
import {QlQuestion} from '../ql-question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';
export type LiteralType = boolean | number | string | Date;

export abstract class Expression {
  constructor(public location: Location) {
  }

  abstract checkType(allQuestions: QlQuestion[]): ExpressionType;

  abstract evaluate(form: FormGroup): LiteralType;

  abstract getVariables(): Variable[];

  protected getLocationErrorMessage(): string {
    return ` between line ${this.location.start.line}` +
      ` and col ${this.location.start.column} and line ${this.location.end.line} and col ${this.location.end.column}`;
  }
}

export class Literal extends Expression {
  constructor(public type: ExpressionType, public value: LiteralType, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return [];
  }

  checkType(): ExpressionType {
    return this.type;
  }

  evaluate(form: FormGroup): LiteralType {
    return this.value;
  }
}
