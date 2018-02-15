import {UnsupportedTypeError} from './errors';
import {QuestionBase} from './question-base';
import {FormGroup} from '@angular/forms';
import {CheckboxQuestion} from './question-checkbox';
import {TextboxQuestion} from './question-textbox';
import {QuestionType} from './question-type';
import {Statement} from './statement';

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
