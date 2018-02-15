import {Statement} from './ast';
import {QuestionBase} from './question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';
import {QuestionType} from './question-type';
import * as _ from 'lodash';

export class If implements Statement {
  constructor(public condition: string, public statements: Statement[]) {
  }

  checkType(allQuestions: Question[]): void {
    const referencedQuestion = allQuestions.find(question => question.name === this.condition);

    if (referencedQuestion === undefined) {
      throw new TypeError(`Cannot find question with identifier ${this.condition}`);
    } else if (referencedQuestion.type !== QuestionType.BOOLEAN) {
      throw new TypeError(`Expected type boolean for ${this.condition} for usage in if statement`);
    }
  }

  getQuestions(): Question[] {
    const subQuestions = [];

    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    return _.flattenDeep(subQuestions);
  }

  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[] {
    const conditionQuestion = formQuestions.filter((q) => q.key === this.condition);
    if (conditionQuestion.length !== 1 || !(conditionQuestion[0].controlType === 'checkbox')) {
      throw new Error('condition not type of checkbox');
    }

    const conditionFunction = ((form: FormGroup) => {
      return form.controls[conditionQuestion[0].key].value === true;
    });

    for (const statement of this.statements) {
      formQuestions = formQuestions.concat(statement.toFormQuestion([], conditionFunction));
    }

    return formQuestions;
  }
}
