import { TestBed, async } from '@angular/core/testing';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import {ParserService} from './services/parser.service';
import {QuestionControlService} from "./services/question-control.service";
import {QuestionService} from "./services/question.service";
import {DynamicFormQuestionComponent} from "./questionmodels/dynamic-form-question.component";
import {BrowserModule} from "@angular/platform-browser";

describe('AppComponent', () => {
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
      providers: [ParserService, QuestionService, QuestionControlService]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
