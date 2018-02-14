import {QuestionBase} from './question-base';
import {CheckboxQuestion} from './question-checkbox';
import {FormGroup} from '@angular/forms';
import {TextboxQuestion} from './question-textbox';
import {UnsupportedTypeError} from './errors';

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

    console.log(formQuestions);
    return formQuestions.sort((a, b) => a.order - b.order);
  }
}

export interface Statement {
  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[];
}

export class Question implements Statement {
  constructor(public name: string, public label: string, public type: QuestionType) {
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


export enum QuestionType {
  INT = 1,
  DECIMAL = 2,
  MONEY = 3,
  BOOLEAN = 4,
  STRING = 5,
  DATE = 6
}
