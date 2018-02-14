import { Component} from '@angular/core';
import {parse} from '../parser/ql-parser';
import {QuestionBase} from './domain/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionService} from './services/question.service';
import {QuestionControlService} from './services/question-control.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  input: string;
  questions: QuestionBase<any>[] = [];
  form: FormGroup;
  formName: string;
  errorMessage: string;

  constructor (private questionService: QuestionService,
               private questionControlService: QuestionControlService) {

  }

  parseInput() {
    try {
      let ast = parse(this.input, {});
      this.questions = this.questionService.toFormQuestions(ast.statements);
      this.form = this.questionControlService.toFormGroup(this.questions);
      this.formName = ast.name;
    } catch (e) {
      this.form = undefined;
      this.formName = undefined;
      this.questions = undefined;
      this.errorMessage = e.message;
    }
  }

  onSubmit() {
    console.log(JSON.stringify(this.form.value));
  }
}
