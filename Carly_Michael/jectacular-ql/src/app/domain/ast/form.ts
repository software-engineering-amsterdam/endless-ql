import {QuestionBase} from '../angular-questions/question-base';
import {Statement} from './statement';
import * as _ from 'lodash';
import {DuplicateIdentifierError, UnknownQuestionError} from '../errors';
import {Question} from './question';
import {Location} from './location';
import {Variable} from './expressions/variable';

export class Form {
  constructor(public name: string, public statements: Statement[], public location: Location) {
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

  checkTypes(): void {
    let allQuestions = [];

    // get all questions in a list for checking on identifiers and types
    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        allQuestions.push(statement.getQuestions());
      }
    }
    allQuestions = _.flatten(allQuestions);

    // check if questions have duplicate identifiers
    if (_.uniqBy(allQuestions, 'name').length < allQuestions.length) {
      const groupedQuestions = _.groupBy(allQuestions, 'name');

      _.forEach(groupedQuestions, (value: Question[], key: string) => {
        if (value.length > 1) {
          throw new DuplicateIdentifierError(`Duplicate question with identifier ${key}`);
        }
      });
    }

    // set referencedQuestions for variables
    let allVariables: Variable[] = [];
    for (const question of allQuestions) {
      allVariables.push(question.getVariables());
    }

    allVariables = _.flatten(allVariables);

    for (const variable of allVariables) {
      const referencedQuestion = allQuestions.find(q => q.name === variable.identifier);

      if (referencedQuestion) {
        variable.referencedQuestion = referencedQuestion;
      } else {
        throw new UnknownQuestionError(`Question with identifier ${variable.identifier} was not found`);
      }
    }

    // check types
    for (const statement of this.statements) {
      statement.checkType(allQuestions);
    }
  }
}
