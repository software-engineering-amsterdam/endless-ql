import { Component} from '@angular/core';
import {parse} from '../parser/ql-parser';
import {parse as parseQls} from '../parser/qls-parser';
import {QuestionBase} from './domain/angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionControlService} from './services/question-control.service';
import {Page, Question as QlsQuestion, Section, Stylesheet, Widget, WidgetType} from './domain/ast/qls';
import {emptyLoc, Form, QuestionType, Question as QlQuestion} from './domain/ast';
import {ParseQlWithQlsFactoryService} from './services/parse-ql-with-qls-factory.service';
import {ParseFactoryInterface} from './services/parse-factory-interface';
import {ParseQlWithDefaultStylingFactoryService} from './services/parse-ql-with-default-styling-factory.service';

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
  qlForm: Form;
  qlsStylesheet: Stylesheet;

  constructor (private questionControlService: QuestionControlService) {

  }

  getQuestionBaseByName(name: string): QuestionBase<any> {
    for (const question of this.questions) {
      if (question.key === name) {
        return question;
      }
    }

    throw new Error(`Couldn't get question '${name}'`);
  }

  getQlQuestionsForSection(section: Section): QuestionBase<any>[] {
    const qlsQuestions = section.getQuestions();
    const questions: QuestionBase<any>[] = [];

    for (const qlsQuestion of qlsQuestions) {
      questions.push(this.getQuestionBaseByName(qlsQuestion.name));
    }

    return questions;
  }

  parseInput() {
    try {
      let factory: ParseFactoryInterface;
      if (this.inputQls && this.inputQls !== '') {
        factory = new ParseQlWithQlsFactoryService(this.input, this.inputQls);
      } else {
        factory = new ParseQlWithDefaultStylingFactoryService(this.input);
      }

      const parseResult = factory.parse();
      this.formName = parseResult.formName;
      this.qlForm = parseResult.qlForm;
      this.qlsStylesheet = parseResult.qlsStylesheet;

      console.log(this.qlsStylesheet);

      // make form
      this.questions = this.qlForm.toFormQuestion();
      this.form = this.questionControlService.toFormGroup(this.questions);
      this.formName = this.qlForm.name;
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
