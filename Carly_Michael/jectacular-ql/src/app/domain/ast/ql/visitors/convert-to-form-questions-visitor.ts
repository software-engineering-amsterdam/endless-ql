import {Statement} from '../statement';
import {StatementVisitor} from './statement-visitor';
import {ExpressionQuestion} from '../expression-question';
import {Form} from '../form';
import {If} from '../if';
import {QlQuestion} from '../ql-question';
import {QuestionBase} from '../../../angular-questions/question-base';
import {QuestionFactory} from '../../../../factories/question-factory';
import {FormGroup} from '@angular/forms';
import {EvaluateExpressionVisitor} from './evaluate-expression-visitor';

export class ConvertToFormQuestionsVisitor implements StatementVisitor<QuestionBase<any>[]> {
  private constructor() {
  }

  static evaluate(stmt: Statement): QuestionBase<any>[] {
    const visitor = new ConvertToFormQuestionsVisitor();
    return stmt.accept(visitor);
  }

  visitExpressionQuestion(stmt: ExpressionQuestion): QuestionBase<any>[] {
    const question = QuestionFactory.toFormQuestion(stmt.name, stmt.label, stmt.type, undefined);
    question.toCalculatedQuestion((form: FormGroup) => {
      return EvaluateExpressionVisitor.evaluate(form, stmt.expression).getValue();
    });
    return [question];
  }

  visitForm(stmt: Form): QuestionBase<any>[] {
    let questionsToReturn: QuestionBase<any>[] = [];

    for (const statement of stmt.statements) {
      questionsToReturn = questionsToReturn.concat(statement.accept(this));
    }

    return questionsToReturn;
  }

  visitIf(stmt: If): QuestionBase<any>[] {
    const questionsToReturn: QuestionBase<any>[] = [];

    for (const statement of stmt.statements) {
      const questions = statement.accept(this);

      for (const question of questions) {
        const previousCondition = question.hiddenCondition;
        question.hiddenCondition = ((form: FormGroup) => {
          const outcome = EvaluateExpressionVisitor.evaluate(form, stmt.condition).getValue();
          if (previousCondition) {
            return previousCondition(form) && outcome;
          }
          return outcome;
        });

        questionsToReturn.push(question);
      }
    }

    for (const statement of stmt.elseStatements) {
      const questions = statement.accept(this);

      for (const question of questions) {
        const previousCondition = question.hiddenCondition;
        question.hiddenCondition = ((form: FormGroup) => {
          const outcome = EvaluateExpressionVisitor.evaluate(form, stmt.condition).getValue();
          if (previousCondition) {
            return previousCondition(form) && !outcome;
          }
          return !outcome;
        });

        questionsToReturn.push(question);
      }
    }

    return questionsToReturn;
  }

  visitQlQuestion(stmt: QlQuestion): QuestionBase<any>[] {
    return [QuestionFactory.toFormQuestion(stmt.name, stmt.label, stmt.type, undefined)];
  }

}
