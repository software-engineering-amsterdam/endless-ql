import {Injectable} from '@angular/core';
import {QuestionBase} from '../domain/question-base';
import {If, Question, QuestionType, Statement} from '../domain/ast';
import {CheckboxQuestion} from '../domain/question-checkbox';
import {TextboxQuestion} from '../domain/question-textbox';
import {UnsupportedTypeError} from '../domain/errors';
import {FormGroup} from '@angular/forms';

@Injectable()
export class QuestionService {
  toFormQuestions(statements: Statement[], condition?: (form: FormGroup) => boolean): QuestionBase<any>[] {
    let formQuestions: QuestionBase<any>[] = [];

    for (const index in statements) {

      const statement = statements[index];

      if (!statement) {
        continue;
      }

      if (statement instanceof Question) {
        const question = <Question>statement;

        const options = {
          key: question.name,
          label: question.label,
          type: this.toHtmlInputType(question.type),
          value: question.type === QuestionType.STRING ? '' : undefined,
          order: index,
          hiddenCondition: condition
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

      if (statement instanceof If) {
        const conditionQuestion = formQuestions.filter((q) => q.key === statement.condition);
        if (conditionQuestion.length !== 1 || !(conditionQuestion[0] instanceof CheckboxQuestion)) {
          throw new Error('condition not type of checkbox');
        }
        formQuestions = formQuestions.concat(this.toFormQuestions(statement.statements, ((form: FormGroup) => {
          return form.controls[conditionQuestion[0].key].value === true;
        })));
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
