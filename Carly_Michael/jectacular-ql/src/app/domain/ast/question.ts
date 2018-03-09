import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionType} from './question-type';
import {Statement} from './statement';
import {Location} from './location';
import {QuestionFactory} from '../../factories/question-factory';

export class Question extends Statement {
  constructor(public name: string, public label: string, public type: QuestionType, location: Location) {
    super(location);
  }

  getQuestions(): Question[] {
    return [this];
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {
    return [QuestionFactory.toFormQuestion(this.name, this.label, this.type, condition)];
  }
}
