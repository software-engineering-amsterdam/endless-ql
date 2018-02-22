import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {CheckboxQuestion} from '../angular-questions/question-checkbox';
import {TextboxQuestion} from '../angular-questions/question-textbox';
import {QuestionType} from './question-type';
import {Statement} from './statement';
import {Question} from './question';
import {Location} from './location';
import {Expression} from './expression';
import {ExpressionType} from './expression-type';
import {UnsupportedTypeError} from '../errors';

export class ExpressionQuestion extends Statement {
  constructor(public name: string, public label: string, public type: QuestionType, public expression: Expression, location: Location) {
    super(location);
  }

  getQuestions(): Question[] {
    return [];
  }

  checkType() {
    if (! this.expressionTypeValidForQuestion(this.expression.checkType())) {
      throw new TypeError(`Expression type  ${this.expression.checkType()} incompatible with question type ${this.type}`
      + this.getLocationErrorMessage());
    }
  }

  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[] {
    const options = {
      key: this.name,
      label: this.label,
      type: this.toHtmlInputType(this.type),
      value: this.type === QuestionType.STRING ? '' : undefined,
      hiddenCondition: condition
    };

    // make a checkbox for a boolean, else make an input
    switch (this.type) {
      case QuestionType.BOOLEAN: {
        formQuestions.push(new CheckboxQuestion(options));
        break;
      }
      default: {
        formQuestions.push(new TextboxQuestion(options));
      }
    }

    return formQuestions;
  }

  expressionTypeValidForQuestion(expressionType: ExpressionType): boolean {
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
