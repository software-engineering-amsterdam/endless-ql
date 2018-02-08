import {Injectable} from '@angular/core';
import {QuestionBase} from '../domain/question-base';
import {Question, QuestionType} from '../domain/ast';
import {CheckboxQuestion} from '../domain/question-checkbox';
import {TextboxQuestion} from '../domain/question-textbox';
import {UnsupportedTypeError} from '../domain/errors';

@Injectable()
export class QuestionService {
  toFormQuestions(questions: Question[]): QuestionBase<any>[] {
    const formQuestions = [];

    for (const index in questions) {
      if (questions[index]) {
        const question = questions[index];
        const options = {
          key: question.name,
          label: question.label,
          type: this.toHtmlInputType(question.type),
          value: question.type === QuestionType.STRING ? '' : undefined,
          order: index
        };

        switch (question.type) {
          case QuestionType.BOOLEAN: {
            formQuestions.push(new CheckboxQuestion(options));
            break;
          }
          default: {
            formQuestions.push(new TextboxQuestion(options));
          }
        }
      }
    }
    return formQuestions.sort((a, b) => a.order - b.order);
  }

  toHtmlInputType(type: QuestionType): string {
    switch (type) {
      case QuestionType.INT : return 'number';
      case QuestionType.DECIMAL: return 'number';
      case QuestionType.MONEY: return 'number';
      case QuestionType.BOOLEAN: return 'boolean';
      case QuestionType.STRING: return 'text';
      case QuestionType.DATE: return 'date';
      default: throw new UnsupportedTypeError('QuestionType is not supported');
    }
  }
}
