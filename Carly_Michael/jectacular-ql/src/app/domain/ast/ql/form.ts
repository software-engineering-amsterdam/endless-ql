import {QuestionBase} from '../../angular-questions/question-base';
import {Statement} from './statement';
import * as _ from 'lodash';
import {QlQuestion} from './ql-question';
import {Location} from '../location';
import {StatementVisitor} from './visitors/statement-visitor';

export class Form extends Statement {
  constructor(readonly name: string, readonly statements: Statement[], readonly location: Location) {
    super(location);
  }

  toFormQuestion(): QuestionBase<any>[] {
    let formQuestions: QuestionBase<any>[] = [];

    for (const index in this.statements) {
      const statement = this.statements[index];

      if (!statement) {
        continue;
      }

      formQuestions = formQuestions.concat(statement.toFormQuestion(formQuestions));
    }

    return formQuestions.sort((a, b) => a.order - b.order);
  }

  getAllQuestions(): QlQuestion[] {
    const allQuestions = [];
    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        allQuestions.push(statement.getQuestions());
      }
    }
    return _.flatten(allQuestions);
  }

  accept<T>(visitor: StatementVisitor<T>): T {
    return visitor.visitForm(this);
  }

  getQuestions(): QlQuestion[] {
    return [];
  }



}
