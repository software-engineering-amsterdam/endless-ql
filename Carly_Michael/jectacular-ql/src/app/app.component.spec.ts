import { TestBed, async } from '@angular/core/testing';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import {QuestionControlService} from './services/question-control.service';
import {QuestionService} from './services/question.service';
import {DynamicFormQuestionComponent} from './questionmodels/dynamic-form-question.component';
import {BrowserModule} from '@angular/platform-browser';

describe('AppComponent', () => {
  let app: AppComponent;

  const validInput =
    `
      form form {
        question1: "IntegerQuestion?"  integer
        question2: "DecimalQuestion?"  decimal
        question3: "MoneyQuestion?"  money
        question4: "BooleanQuestion?"  boolean
        question5: "StringQuestion?"  string
 	      question6: "DateQuestion?"  date
 	      if (question4) {
 	        question7: "ifQuestion" integer
 	      }
      }
  `;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        DynamicFormQuestionComponent
      ],
      imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule
      ],
      providers: [QuestionService, QuestionControlService]
    }).compileComponents();
  }));

  beforeEach(() => {
    const fixture = TestBed.createComponent(AppComponent);
    app = fixture.debugElement.componentInstance;
  });

  it('should parse input', () => {
    app.input = validInput;
    app.parseInput();
    expect(app.formName).toBe('form');
  });
});
