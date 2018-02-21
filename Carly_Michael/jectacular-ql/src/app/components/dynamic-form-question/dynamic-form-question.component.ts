import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionBase } from '../../domain/angular-questions/question-base';

@Component({
  selector: 'app-question',
  templateUrl: './dynamic-form-question.component.html'
})
export class DynamicFormQuestionComponent implements OnInit {
  @Input() question: QuestionBase<any>;
  @Input() form: FormGroup;

  get isValid() { return this.form.controls[this.question.key].valid; }

  onCheckboxChange(question: QuestionBase<any>, event) {
    this.form.controls[this.question.key].setValue(event.target.checked);
  }

  ngOnInit(): void {
    this.form.valueChanges.subscribe(() => {
      // this subscribe is necessary to have angular auto-update the hiddenCondition function
    });
  }
}
