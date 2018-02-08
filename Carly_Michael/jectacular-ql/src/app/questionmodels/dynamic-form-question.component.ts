import { Component, Input } from '@angular/core';
import { FormGroup }        from '@angular/forms';

import { QuestionBase }     from './question-base';

@Component({
  selector: 'app-question',
  templateUrl: './dynamic-form-question.component.html'
})
export class DynamicFormQuestionComponent {
  @Input() question: QuestionBase<any>;
  @Input() form: FormGroup;
  get isValid() { return this.form.controls[this.question.key].valid; }
  tempChange(question : QuestionBase<any>, event) {
    //key.value = !key.value;
    //event.target.checked ? question.value = true : false;
    this.question.value = event.target.checked;
    console.log(question, this.question, event);
  }
}
