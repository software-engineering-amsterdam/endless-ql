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

    const options = {
      key: name,
      label: label,
      type: QuestionFactory.toHtmlInputType(type),
      value: undefined,
      hiddenCondition: condition
    };

    let formQuestionsToReturn: QuestionBase<any>;

    if (type === QuestionType.BOOLEAN) {
      formQuestionsToReturn = new BooleanQuestion(options);
    } else {
      formQuestionsToReturn = new InputQuestion(options);
    }

    return formQuestionsToReturn;
  }
}
