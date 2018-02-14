import {QuestionBase} from './question-base';
import {CheckboxQuestion} from './question-checkbox';
import {FormGroup} from '@angular/forms';
import {TextboxQuestion} from './question-textbox';
import {DuplicateIdentifierError, UnsupportedTypeError} from './errors';
import * as _ from 'lodash';

export enum QuestionType {
  INT = 1,
  DECIMAL = 2,
  MONEY = 3,
  BOOLEAN = 4,
  STRING = 5,
  DATE = 6
}

export class Form {
  constructor(public name: string, public statements: Statement[]) {}

  toFormQuestion(): QuestionBase<any>[] {
    let formQuestions: QuestionBase<any>[] = [];

    for (const index in this.statements) {

      const statement = this.statements[index];

      if (!statement) {
        continue;
      }

      formQuestions = statement.toFormQuestion(formQuestions);
    }

    return formQuestions.sort((a, b) => a.order - b.order);
  }

  checkTypes(): void {
    let allQuestions = [];

    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        allQuestions.push(statement.getQuestions());
      }
    }
    allQuestions = _.flatten(allQuestions);

    console.log(allQuestions);

    if (_.uniqBy(allQuestions, 'name').length < allQuestions.length) {
      console.log('in if');
      const groupedQuestions = _.groupBy(allQuestions, 'name');

      console.log(groupedQuestions);

      _.forEach(groupedQuestions, (value: Question[], key: string) =>  {
        if (value.length > 1) {
          throw new DuplicateIdentifierError(`Duplicate question with identifier ${key}`);
        }
      });
    }

    for (const statement of this.statements) {
      statement.checkType(allQuestions);
    }
  }
}

export interface Statement {
  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[];
  getQuestions(): Question[];
  checkType(allQuestions: Question[]): void;
}

export class Question implements Statement {
  constructor(public name: string, public label: string, public type: QuestionType) {
  }

  getQuestions(): Question[] {
    return [this];
  }

  checkType(allQuestions: Question[]): void {
    return;
  }

  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[] {
    const options = {
      key: this.name,
      label: this.label,
      type: this.toHtmlInputType(this.type),
      value: this.type === QuestionType.STRING ? '' : undefined,
      hiddenCondition: condition
    };

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

  private toHtmlInputType(type: QuestionType): string {
    switch (type) {
      case QuestionType.INT : return 'number';
      case QuestionType.DECIMAL: return 'number';
      case QuestionType.MONEY: return 'number';
      case QuestionType.BOOLEAN: return 'boolean';
      case QuestionType.STRING: return 'text';
      case QuestionType.DATE: return 'date';
      default: throw new UnsupportedTypeError('QuestionType is not supported');
    }
  }
}

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
