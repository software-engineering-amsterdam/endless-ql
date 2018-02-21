import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import {DynamicFormQuestionComponent} from './components/dynamic-form-question/dynamic-form-question.component';
import {QuestionControlService} from './services/question-control.service';


@NgModule({
  declarations: [
    AppComponent,
    DynamicFormQuestionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [QuestionControlService],
  bootstrap: [AppComponent]
})
export class AppModule { }
