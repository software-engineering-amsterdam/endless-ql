import {QuestionBase} from '../../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';
import {Location} from '../location';
import {LiteralType} from './expressions/expression';
import {Variable} from './expressions/variable';

  export abstract class Statement {
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
