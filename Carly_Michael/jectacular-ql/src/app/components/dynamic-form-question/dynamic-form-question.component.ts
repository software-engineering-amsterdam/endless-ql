import { Component, Input, OnInit } from '@angular/core';
import {AbstractControl, FormGroup} from '@angular/forms';
import { QuestionBase } from '../../domain/angular-questions/question-base';

@Component({
  selector: 'app-question',
  templateUrl: './dynamic-form-question.component.html'
})
export class DynamicFormQuestionComponent implements OnInit {
  @Input() question: QuestionBase<any>;
  @Input() form: FormGroup;
  control: AbstractControl;
  controlValue: any;

  get isInvalid() {
    return this.form.controls[this.question.key].invalid;
  }

  onCheckboxChange(question: QuestionBase<any>, event) {
    this.form.controls[this.question.key].setValue(event.target.checked);
  }

  ngOnInit(): void {
    this.control = this.form.controls[this.question.key];
    this.controlValue = this.form.controls[this.question.key].value;
    this.form.valueChanges.subscribe((form) => {
      if (this.question.readonly) {
        const currentValue = this.control.value;
        const newValue = this.question.calculateValue(this.form);
        console.log(`calculating value for readonly question, old = ${currentValue}, new = ${newValue}`);
        if (currentValue !== newValue) {
          console.log('calculating new value with the new form stuff');
          //this.control.setValue(newValue);
        }
      }
      // this subscribe is necessary to have angular auto-update the hiddenCondition function
    });

    this.control.valueChanges.subscribe((value) => console.log(`new value ${value} for ${this.question.key}`))
  }

}
