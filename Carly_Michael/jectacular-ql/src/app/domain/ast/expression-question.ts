import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {CheckboxQuestion} from '../angular-questions/question-checkbox';
import {TextboxQuestion} from '../angular-questions/question-textbox';
import {QuestionType} from './question-type';
import {Statement} from './statement';
import {Question} from './question';
import {Location} from './location';
import {Expression} from './expressions/expression';
import {ExpressionType} from './expressions/expression-type';
import {UnknownQuestionError, UnsupportedTypeError} from '../errors';
import * as _ from 'lodash';
import {Variable} from './expressions/variable';

export class ExpressionQuestion extends Question {
  constructor(name: string, label: string, type: QuestionType, public expression: Expression, location: Location) {
    super(name, label, type, location);
  }

  getVariables(): Variable[] {
    return this.expression.getVariables();
  }

  checkType(allQuestions: Question[]) {
    if (! this.expressionTypeValidForQuestion(this.expression.checkType(allQuestions), allQuestions)) {
      throw new TypeError(`Expression type ${this.expression.checkType(allQuestions)} incompatible with question type ${this.type}`
      + this.getLocationErrorMessage());
    }
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {
    const options = {
      key: this.name,
      label: this.label,
      type: Statement.toHtmlInputType(this.type),
      value: undefined,
      hiddenCondition: condition,
      calculateValue: (form: FormGroup) => this.expression.evaluate(form),
      readonly: true
    };

    let formQuestionsToReturn: QuestionBase<any>[] = [];
    // make a checkbox for a boolean, else make an input
    switch (this.type) {
      case QuestionType.BOOLEAN: {
        formQuestionsToReturn = [new CheckboxQuestion(options)];
        break;
      }
      default: {
        formQuestionsToReturn = [new TextboxQuestion(options)];
      }
    }

    console.log('formquestions in expression question', formQuestionsToReturn);
    return formQuestionsToReturn;
  }

  expressionTypeValidForQuestion(expressionType: ExpressionType, allQuestions: Question[]): boolean {
    switch (expressionType) {
      case ExpressionType.NUMBER:
        return this.type === QuestionType.MONEY || this.type === QuestionType.INT || this.type === QuestionType.DECIMAL;
      case ExpressionType.BOOLEAN:
        return this.type === QuestionType.BOOLEAN;
      case ExpressionType.DATE:
        return this.type === QuestionType.DATE;
      case ExpressionType.STRING:
        return this.type === QuestionType.STRING;
      default: throw new UnsupportedTypeError(`ExpressionType ${expressionType} is unknown`);
    }
  }
}
