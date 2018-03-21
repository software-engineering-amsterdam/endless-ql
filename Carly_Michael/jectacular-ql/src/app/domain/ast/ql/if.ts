import {Statement} from './statement';
import {QuestionBase} from '../../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QlQuestion} from './ql-question';
import * as _ from 'lodash';
import {Location} from '../location';
import {Expression, LiteralType} from './expressions/expression';
import {EvaluateExpressionVisitor} from './visitors/evaluate-expression-visitor';
import {StatementVisitor} from './visitors/statement-visitor';

export class If extends Statement {
  constructor(
    readonly condition: Expression,
    readonly statements: Statement[],
    readonly elseStatements: Statement[],
    location: Location) {
    super(location);
  }

  getQuestions(): QlQuestion[] {
    const subQuestions = [];

    // get questions of statements in body of the if
    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    for (const statement of this.elseStatements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    return _.flattenDeep(subQuestions);
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {

    // generate function that should be evaluated for the condition
    const conditionFunction = ((form: FormGroup) => {
      const outcome = EvaluateExpressionVisitor.evaluate(form, this.condition).getValue();
      if (condition) {
        return condition(form) && outcome;
      }
      return outcome;
    });

    const elseConditionFunction = ((form: FormGroup) => {
      return !conditionFunction(form);
    });

    return this.generateQuestionsForBody(formQuestions, conditionFunction, elseConditionFunction);
  }

  accept<T>(visitor: StatementVisitor<T>): T {
    return visitor.visitIf(this);
  }

  private generateQuestionsForBody(formQuestions: ReadonlyArray<QuestionBase<any>>,
                                   conditionFunction: (form: FormGroup) => LiteralType,
                                   elseConditionFunction: (form: FormGroup) => LiteralType): ReadonlyArray<QuestionBase<any>> {
    let formQuestionsToReturn: QuestionBase<any>[] = [];
    for (const statement of this.statements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, conditionFunction));
    }

    for (const statement of this.elseStatements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, elseConditionFunction));
    }

    return formQuestionsToReturn;
  }
}
