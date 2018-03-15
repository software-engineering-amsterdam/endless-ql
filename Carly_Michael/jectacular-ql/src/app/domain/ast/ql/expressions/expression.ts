import {ExpressionType} from './expression-type';
import {Location} from '../../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';
import {QuestionType, QuestionTypeUtil} from '../../question-type';
import {UnsupportedTypeError} from '../../../errors';
export type LiteralType = boolean | number | string | Date;

export abstract class Expression {
  constructor(public location: Location) {
  }

  abstract checkType(allQuestions: Question[]): ExpressionType;

  abstract evaluate(form: FormGroup): LiteralType;

  abstract getVariables(): Variable[];

  protected getLocationErrorMessage(): string {
    return ` between line ${this.location.start.line}` +
      ` and col ${this.location.start.column} and line ${this.location.end.line} and col ${this.location.end.column}`;
  }

  // TODO: see if switch can be deleted
  protected toExpressionType(questionType: QuestionType): ExpressionType {
    switch (questionType) {
      case QuestionType.INT:
        return ExpressionType.NUMBER;
      case QuestionType.STRING:
        return ExpressionType.STRING;
      case QuestionType.BOOLEAN:
        return ExpressionType.BOOLEAN;
      case QuestionType.DATE:
        return ExpressionType.DATE;
      default:
        throw new UnsupportedTypeError(`QuestionType ${QuestionTypeUtil.toString(questionType)} is not supported`);
    }
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
