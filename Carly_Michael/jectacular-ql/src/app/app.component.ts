import { Component} from '@angular/core';
import {parse} from '../parser/ql-parser';
import {parse as parseQls} from '../parser/qls-parser';
import {QuestionBase} from './domain/angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionControlService} from './services/question-control.service';
import {Page, Question as QlsQuestion, Section, Stylesheet, Widget, WidgetType} from './domain/ast/qls';
import {emptyLoc, Form, QuestionType, Question as QlQuestion} from './domain/ast';

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
  astQl: Form;
  astQls: Stylesheet;

  constructor (private questionControlService: QuestionControlService) {

  }

  // TODO move into factory pattern mentioned below
  qlQuestionTypeToQlsType(qlType: QuestionType): WidgetType {
    switch (qlType) {
      case QuestionType.INT:
      case QuestionType.DECIMAL:
        return WidgetType.SPINBOX;
      case QuestionType.BOOLEAN:
        return WidgetType.CHECKBOX;
      case QuestionType.DATE:
      case QuestionType.STRING:
      default:
        return WidgetType.NONE;
        // throw new Error('TODO implementation');
    }
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
      // parse input to tree
      this.astQl = parse(this.input, {});
      // check form
      this.astQl.checkForm();

      if (this.inputQls && this.inputQls !== '') {
        this.astQls = parseQls(this.inputQls, {});
      } else {
        // generate default styling for all questions in case input is empty
        // TODO move this into factory pattern
        const qlsQuestions: QlsQuestion[] = [];

        for (const qlQuestion of this.astQl.getAllQuestions()) {
          const widget = new Widget(this.qlQuestionTypeToQlsType(qlQuestion.type), []);
          qlsQuestions.push(new QlsQuestion(qlQuestion.name, widget, emptyLoc));
        }

        const section = new Section('default section', [], qlsQuestions, emptyLoc);
        const page = new Page('default page', [section], emptyLoc);
        this.astQls = new Stylesheet('default implementation', [page], emptyLoc);
      }

      // make form
      this.questions = this.astQl.toFormQuestion();
      this.form = this.questionControlService.toFormGroup(this.questions);
      this.formName = this.astQl.name;
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
