import {Component, isDevMode} from '@angular/core';
import {parse} from '../parser/ql-parser';
import {parse as parseQls} from '../parser/qls-parser';
import {QuestionBase} from './domain/angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionControlService} from './services/question-control.service';
import {Page, Question as QlsQuestion, Section, Style, Stylesheet, Widget, WidgetType} from './domain/ast/qls';
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
  qlsToQlQuestionDictionary: Map<string, QuestionBase<any>> = new Map<string, QuestionBase<any>>();

  constructor (private questionControlService: QuestionControlService) {
  this.inputQls = 'stylesheet "taxOfficeExample"\n' +
    '{\n' +
    '  page "Housing"\n' +
    '  {\n' +
    '    section "Buying"\n' +
    '    {\n' +
    '      question question3\n' +
    '        widget checkbox\n' +
    '    }\n' +
    '    section "Loaning"\n' +
    '      question question4\n' +
    '  }\n' +
    '\n' +
    '  page "Selling"\n' +
    '  {\n' +
    '    section "Selling"\n' +
    '    {\n' +
    '      question question5\n' +
    '        widget radio("Yes", "No")\n' +
    '      section "You sold a house"\n' +
    '      {\n' +
    '        question question1\n' +
    '          widget spinbox\n' +
    '        question question2\n' +
    '          widget spinbox\n' +
    '        question question6\n' +
    '        default integer\n' +
    '        {\n' +
    '          width: 400\n' +
    '          font: "Arial"\n' +
    '          fontsize: 14\n' +
    '          color: #999999\n' +
    '          widget spinbox\n' +
    '        }\n' +
    '      }\n' +
    '    }\n' +
    '    default boolean widget radio("Yes", "No")\n' +
    '  }\n' +
    '}';

  this.input = 'form form {\n' +
    '  question1: "IntegerQuestion?"  integer\n' +
    '  question2: "DecimalQuestion?"  integer\n' +
    '  question3: "BooleanQuestion?"  boolean\n' +
    '  question4: "StringQuestion?"  string\n' +
    '  question5: "DateQuestion?"  date\n' +
    '  if (question3) {\n' +
    '    question6: "ifQuestion" integer\n' +
    '  }\n' +
    '}';
  }

  getQuestionBaseByName(name: string): QuestionBase<any> {
    const question = this.qlsToQlQuestionDictionary[name];

    if (!question) {
      throw new Error(`Couldn't get question '${name}'`);
    }

    return question;
  }

  createQuestionMappingCache() {
    for (const qlsQuestion of this.qlsStylesheet.getQuestions([])) {
      let questionBase: QuestionBase<any>;

      for (const q of this.questions) {
        if (q.key === qlsQuestion.question.name) {
          questionBase = q;
          break;
        }
      }

      if (!questionBase) {
        throw new Error(`Couldn't find question ${qlsQuestion.question.name}`);
      }

      this.qlsToQlQuestionDictionary[qlsQuestion.question.name] = questionBase;
    }
  }

  assignStyleToQuestions() {
    for (const qlsQuestionWithAppliedStyles of this.qlsStylesheet.getQuestions([])) {
      const question = this.qlsToQlQuestionDictionary[qlsQuestionWithAppliedStyles.question.name];

      if (qlsQuestionWithAppliedStyles.styles && qlsQuestionWithAppliedStyles.styles.length > 0) {
        const styles: Map<string, Style> = new Map<string, Style>();

        for (const style of qlsQuestionWithAppliedStyles.styles) {
          styles[style.name] = style;
        }

        let questionStyle = '';

        for (const [, v] of styles) {
          questionStyle += v.name + ': ' + v.value + ';';
        }
        //console.log(question.name, questionStyle);
        question.style = questionStyle;
      }
    }
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

      // make form
      this.questions = this.qlForm.toFormQuestion();
      this.form = this.questionControlService.toFormGroup(this.questions);
      this.formName = this.qlForm.name;
      this.errorMessage = undefined;

      this.createQuestionMappingCache();
      //this.assignStyleToQuestions();
    } catch (e) {
      this.form = undefined;
      this.formName = undefined;
      this.questions = undefined;
      this.errorMessage = e.message;

      if (isDevMode()) {
        console.log(e);
      }
    }
    this.payload = undefined;
  }

  onSubmit() {
    this.payload = JSON.stringify(this.form.getRawValue());
    console.log(JSON.stringify(this.form.getRawValue()));
  }
}
