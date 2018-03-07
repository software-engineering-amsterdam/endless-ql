import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTabsModule, MatListModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatListModule
  ],
  exports: [
    MatTabsModule,
    MatListModule
  ],
  providers: [QuestionControlService],
  bootstrap: [AppComponent]
})
export class AppModule { }
