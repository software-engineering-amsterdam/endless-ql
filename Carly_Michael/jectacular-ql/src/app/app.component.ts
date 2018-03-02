import { Component} from '@angular/core';
import {parse} from '../parser/ql-parser';
import {parse as parseQls} from '../parser/qls-parser';
import {QuestionBase} from './domain/angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionControlService} from './services/question-control.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  input: string;
  inputQls: string;
  questions: QuestionBase<any>[] = [];
  form: FormGroup;
  formName: string;
  errorMessage: string;
  payload: string;

  constructor (private questionControlService: QuestionControlService) {

  }

  parseInput() {
    try {
      if (this.inputQls && this.inputQls !== '') {
        const astQls = parseQls(this.inputQls, {});
      }
      // parse input to tree
      const ast = parse(this.input, {});
      // check form
      ast.checkForm();
      // make form
      this.questions = ast.toFormQuestion();
      this.form = this.questionControlService.toFormGroup(this.questions);
      this.formName = ast.name;
      this.errorMessage = undefined;
    } catch (e) {
      this.form = undefined;
      this.formName = undefined;
      this.questions = undefined;
      this.errorMessage = e.message;
    }
    this.payload = undefined;
  }

  onSubmit() {
    this.payload = JSON.stringify(this.form.getRawValue());
    console.log(JSON.stringify(this.form.getRawValue()));
  }
}
