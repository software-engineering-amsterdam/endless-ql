import {QuestionBase} from '../../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QlQuestion} from './ql-question';
import {Location} from '../location';
import {LiteralType} from './expressions/expression';
import {Variable} from './expressions/variable';
import {StatementVisitor} from './visitors/statement-visitor';

export abstract class Statement {
  constructor(readonly location: Location) {}

  abstract toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                          condition?: (form: FormGroup) => LiteralType): ReadonlyArray<QuestionBase<any>>;
  abstract getQuestions(): QlQuestion[];

  getVariables(): Variable[] {
    return [];
  }

  checkDependencies(): void {
    return;
  }

  abstract accept<T>(visitor: StatementVisitor<T>): T;
}
