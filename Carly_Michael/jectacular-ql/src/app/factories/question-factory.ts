import {QuestionType} from '../domain/ast/question-type';
import {QuestionBase} from '../domain/angular-questions/question-base';
import {InputQuestion} from '../domain/angular-questions/input-question';
import {BooleanQuestion} from '../domain/angular-questions/boolean-question';
import {FormGroup} from '@angular/forms';
import {Style, Widget} from '../domain/ast/qls';
import {StyledQuestion} from '../domain/angular-questions/styled-question';

export class QuestionFactory {
  static toFormQuestion(name: string,
                        label: string,
                        type: QuestionType<any>,
                        condition?: (form: FormGroup) => boolean): QuestionBase<any> {

    let formQuestionsToReturn: QuestionBase<any>;

    if (type.toString() === 'boolean') {
      formQuestionsToReturn = new BooleanQuestion(name, label, type.getDefaultValue(), type.toHtmlInputType(), condition);
    } else {
      formQuestionsToReturn = new InputQuestion(name, label, type.getDefaultValue(), type.toHtmlInputType(), condition);
    }

    return formQuestionsToReturn;
  }

  static applyStylesToFormQuestion<T>(question: QuestionBase<any>, widget: Widget, styles: Style[]): StyledQuestion<T> {

    let styleMap: { [key: string]: string } = {};

    for (const style of styles) {
      styleMap[style.name] = style.value.getValueAsString();
    }

    return new StyledQuestion<T>(
      question.key,
      question.label,
      question.value,
      question.type,
      question.controlType,
      question.hiddenCondition,
      widget,
      styleMap
    );
  }
}
