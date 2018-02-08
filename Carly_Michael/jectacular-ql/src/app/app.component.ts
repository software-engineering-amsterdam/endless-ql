import { Component} from '@angular/core';
import {ParserService} from './services/parser.service';
import {QuestionBase} from "./questionmodels/question-base";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TextboxQuestion} from "./questionmodels/question-textbox";
import {DropdownQuestion} from "./questionmodels/question-dropdown";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  input: string;
  questions: QuestionBase<any>[] = [];
  form: FormGroup;

  constructor (private parser: ParserService) {
    this.questions = this.getQuestions();
    this.form = this.toFormGroup(this.questions);
  }

  parseInput() {
    console.log(this.parser.parseInput(this.input));
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
}
