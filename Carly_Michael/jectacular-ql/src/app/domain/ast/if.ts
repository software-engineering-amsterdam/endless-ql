import {Statement} from './statement';
import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';
import {QuestionType} from './question-type';
import * as _ from 'lodash';
import {UnknownQuestionError, TypeError} from '../errors';
import {Location} from './location';

export class If extends Statement {
  constructor(public condition: string, public statements: Statement[], public elseStatements: Statement[], location: Location) {
    super(location);
  }

  checkType(allQuestions: Question[]): void {
    // find the question that is referenced to in the if condition
    const referencedQuestion = allQuestions.find(question => question.name === this.condition);

    // throw errors if it is not available or if the type is wrong
    if (referencedQuestion === undefined) {
      throw new UnknownQuestionError(
        `Cannot find question with identifier ${this.condition} for if statement ` + this.getLocationErrorMessage());
    } else if (referencedQuestion.type !== QuestionType.BOOLEAN) {
      throw new TypeError(`Expected type boolean for ${this.condition} for usage in if statement ` + this.getLocationErrorMessage());
    }
  }

  getQuestions(): Question[] {
    const subQuestions = [];

    // get questions of statements in body of the if
    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    for (const statement of this.elseStatements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    return _.flattenDeep(subQuestions);
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {
    const conditionQuestion = formQuestions.filter((q) => q.key === this.condition);
    if (conditionQuestion.length !== 1 || !(conditionQuestion[0].controlType === 'checkbox')) {
      throw new TypeError('condition not type of checkbox');
    }


    // generate function that should be evaluated for the condition
    const conditionFunction = ((form: FormGroup) => {
      if (condition) {
        return condition(form) && form.controls[conditionQuestion[0].key].value === true;
      }
      return form.controls[conditionQuestion[0].key].value === true;
    });

    const elseConditionFunction = ((form: FormGroup) => {
      return !conditionFunction(form);
    });

    let formQuestionsToReturn: QuestionBase<any>[] = [];
    // generate questions for statements in body
    for (const statement of this.statements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, conditionFunction));
    }

    for (const statement of this.elseStatements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, elseConditionFunction));
    }

    return formQuestionsToReturn;
  }
}
