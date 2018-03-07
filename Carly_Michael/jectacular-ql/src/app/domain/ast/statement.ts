import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';
import {Location} from './location';
import {QuestionType} from './question-type';
import {UnsupportedTypeError} from '../errors';
import {LiteralType} from './expressions/expression';
import {Variable} from './expressions/variable';

  export abstract class Statement {
  protected static toHtmlInputType(type: QuestionType): string {
    switch (type) {
      case QuestionType.INT : return 'number';
      case QuestionType.DECIMAL: return 'number';
      case QuestionType.BOOLEAN: return 'boolean';
      case QuestionType.STRING: return 'text';
      case QuestionType.DATE: return 'date';
      default: throw new UnsupportedTypeError('QuestionType is not supported');
    }
  }

  constructor(public location: Location) {}

  abstract toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                          condition?: (form: FormGroup) => LiteralType): ReadonlyArray<QuestionBase<any>>;
  abstract getQuestions(): Question[];

  getVariables(): Variable[] {
    return [];
  }

  checkType(allQuestions: Question[]): void {
    return;
  }

  checkDependencies(): void {
    return;
  }

  protected getLocationErrorMessage(): string {
    return ` between line ${this.location.start.line}` +
      ` and col ${this.location.start.column} and line ${this.location.end.line} and col ${this.location.end.column}`;
  }
}
