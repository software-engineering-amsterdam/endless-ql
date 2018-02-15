import {QuestionBase} from './question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';

export interface Statement {
  toFormQuestion(formQuestions: QuestionBase<any>[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[];
  getQuestions(): Question[];
  checkType(allQuestions: Question[]): void;
}
