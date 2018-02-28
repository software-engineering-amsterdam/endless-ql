import {QuestionBase} from '../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {CheckboxQuestion} from '../angular-questions/question-checkbox';
import {TextboxQuestion} from '../angular-questions/question-textbox';
import {QuestionType} from './question-type';
import {Statement} from './statement';
import {Location} from './location';

export class Question extends Statement {
  constructor(public name: string, public label: string, public type: QuestionType, location: Location) {
    super(location);
  }

  getQuestions(): Question[] {
    return [this];
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>, condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {
    const options = {
      key: this.name,
      label: this.label,
      type: Statement.toHtmlInputType(this.type),
      value: this.type === QuestionType.STRING ? '' : undefined,
      hiddenCondition: condition
    };


    let formQuestionsToReturn: QuestionBase<any>[] = [];
    // make a checkbox for a boolean, else make an input
    switch (this.type) {
      case QuestionType.BOOLEAN: {
        formQuestionsToReturn = [new CheckboxQuestion(options)];
        break;
      }
      default: {
        formQuestionsToReturn = [new TextboxQuestion(options)];
      }
    }

    return formQuestionsToReturn;
  }
}
