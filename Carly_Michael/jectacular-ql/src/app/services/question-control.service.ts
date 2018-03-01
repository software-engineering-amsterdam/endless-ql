import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { QuestionBase } from '../domain/angular-questions/question-base';

@Injectable()
export class QuestionControlService {
  constructor() { }

  toFormGroup(questions: ReadonlyArray<QuestionBase<any>>) {
    const group: any = {};

    questions.forEach(question => {
      group[question.key] = question.required ? new FormControl({value: question.value || ''/*, disabled: question.readonly*/},
        Validators.required)
        : new FormControl({value: question.value || ''/*, disabled: question.readonly*/});
    });
    return new FormGroup(group);
  }
}
