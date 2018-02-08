import { Component} from '@angular/core';
import {ParserService} from './services/parser.service';
import {QuestionBase} from "./questionmodels/question-base";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TextboxQuestion} from "./questionmodels/question-textbox";
import {DropdownQuestion} from "./questionmodels/question-dropdown";
import {CheckboxQuestion} from "./questionmodels/question-checkbox";
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

  constructor (private parser: ParserService,
               private questionService: QuestionService,
               private questionControlService: QuestionControlService) {

  }

  parseInput() {
    console.log(this.parser.parseInput(this.input));
    const ast = this.parser.parseInput(this.input);
    this.questions = this.questionService.toFormQuestions(ast.questions);
    this.form = this.questionControlService.toFormGroup(this.questions);
    this.formName = ast.name;
  }

  getQuestions() {

    let questions: QuestionBase<any>[] = [

      new DropdownQuestion({
        key: 'brave',
        label: 'Bravery Rating',
        options: [
          {key: 'solid',  value: 'Solid'},
          {key: 'great',  value: 'Great'},
          {key: 'good',   value: 'Good'},
          {key: 'unproven', value: 'Unproven'}
        ],
        order: 3
      }),

      new TextboxQuestion({
        key: 'firstName',
        label: 'First name',
        value: 'Bombasto',
        required: true,
        order: 1
      }),

      new TextboxQuestion({
        key: 'emailAddress',
        label: 'Email',
        type: 'email',
        order: 2
      }),

      new CheckboxQuestion({
        key: 'checkBoxQuestion',
        label: 'Question',
        type: 'checkbox',
        value: false,
        order: 4
      })
    ];

    return questions.sort((a, b) => a.order - b.order);
  }

  toFormGroup(questions: QuestionBase<any>[] ) {
    let group: any = {};

    questions.forEach(question => {
      group[question.key] = question.required ? new FormControl(question.value || '', Validators.required)
        : new FormControl(question.value || '');
    });
    return new FormGroup(group);
  }

  onSubmit() {
    console.log(JSON.stringify(this.form.value));
  }
}
