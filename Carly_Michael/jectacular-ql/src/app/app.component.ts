import { Component} from '@angular/core';
import {ParserService} from './services/parser.service';
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

  constructor (private parser: ParserService,
               private questionService: QuestionService,
               private questionControlService: QuestionControlService) {

  }

  parseInput() {
    try {
      const ast = this.parser.parseInput(this.input);
      this.questions = this.questionService.toFormQuestions(ast.questions);
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
