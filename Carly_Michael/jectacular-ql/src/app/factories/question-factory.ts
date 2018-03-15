import {QuestionType} from '../domain/ast/question-type';
import {QuestionBase} from '../domain/angular-questions/question-base';
import {InputQuestion} from '../domain/angular-questions/input-question';
import {BooleanQuestion} from '../domain/angular-questions/boolean-question';
import {FormGroup} from '@angular/forms';
import {UnsupportedTypeError} from '../domain/errors';

export class QuestionFactory {
  static toHtmlInputType(type: QuestionType): string {
    switch (type) {
      case QuestionType.INT : return 'number';
      case QuestionType.BOOLEAN: return 'boolean';
      case QuestionType.STRING: return 'text';
      case QuestionType.DATE: return 'date';
      default: throw new UnsupportedTypeError('QuestionType is not supported');
    }
  }

  static toFormQuestion(name: string,
                        label: string,
                        type: QuestionType,
                        condition?: (form: FormGroup) => boolean
                        ): QuestionBase<any> {

    let formQuestionsToReturn: QuestionBase<any>;

    if (type === QuestionType.BOOLEAN) {
      formQuestionsToReturn = new BooleanQuestion(name, label, false, QuestionFactory.toHtmlInputType(type), condition);
    } else {
      formQuestionsToReturn = new InputQuestion(name, label, this.getDefaultValue(type), QuestionFactory.toHtmlInputType(type), condition);
    }

    return formQuestionsToReturn;
  }

  static getDefaultValue(type: QuestionType) {
    switch (type) {
      case QuestionType.INT : return 0;
      case QuestionType.BOOLEAN: return false;
      case QuestionType.STRING: return '';
      case QuestionType.DATE: return '';
      default: throw new UnsupportedTypeError('QuestionType is not supported');
    }
  }
}
