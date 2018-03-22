import {ExpressionType, ExpressionTypeUtil} from '../expressions/expression-type';
import {StatementVisitor} from './statement-visitor';
import {Statement} from '../statement';
import {QlQuestion} from '../ql-question';
import {If} from '../if';
import {Form} from '../form';
import {ExpressionQuestion} from '../expression-question';
import {CheckExpressionTypeVisitor} from './check-expression-type-visitor';
import {locationToReadableMessage} from '../../location';
import {DuplicateIdentifierError, ImpossibleIfConditionError, TypeError, UnknownQuestionError} from '../../../errors';
import * as _ from 'lodash';
import {CollectStatementVariablesVisitor} from './collect-statement-variables-visitor';
import {CollectExpressionVariablesVisitor} from './collect-expression-variables-visitor';
import {CollectQuestionsVisitor} from './collect-questions-visitor';

export class CheckStatementTypeVisitor implements StatementVisitor<void> {
  private constructor(readonly allQuestions: ReadonlyArray<QlQuestion>) { }

  static evaluate(allQuestions: ReadonlyArray<QlQuestion>, stmt: Statement): void {
    const visitor = new CheckStatementTypeVisitor(allQuestions);
    stmt.accept(visitor);
  }

  visitExpressionQuestion(stmt: ExpressionQuestion): void {
    const expressionType = CheckExpressionTypeVisitor.evaluate(stmt.expression);
    if (! stmt.expressionTypeValidForQuestion(expressionType)) {
      throw new TypeError(`Expression type ${ExpressionTypeUtil.toString(expressionType)} ` +
        `incompatible with question type ${stmt.type.toString()}`
        + locationToReadableMessage(stmt.location));
    }
  }

  visitForm(stmt: Form): void {
    this.checkDuplicateIdentifiers();
    this.setVariableReferences(stmt);

    for (const subStmt of stmt.statements) {
      subStmt.accept(this);
    }
  }

  visitIf(stmt: If): void {
    const expressionType = CheckExpressionTypeVisitor.evaluate(stmt.condition);

    // throw errors if it is not available or if the type is wrong
    if (expressionType !== ExpressionType.BOOLEAN) {
      throw new TypeError(`Expected type boolean for ${ExpressionTypeUtil.toString(expressionType)} for usage in if statement `
        + locationToReadableMessage(stmt.location));
    }

    // check if any of the referenced question(s) in the condition point to questions in the body
    const variables = CollectExpressionVariablesVisitor.evaluate(stmt.condition);
    const questions = CollectQuestionsVisitor.evaluate(stmt);

    for (const variable of variables) {
      const question = questions.find(q => q.name === variable.identifier);

      if (question) {
        throw new ImpossibleIfConditionError(`if statement ${locationToReadableMessage(stmt.location)}` +
          `has question '${question.name}' both in condition and in body`);
      }
    }

    for (const subStmt of stmt.statements) {
      subStmt.accept(this);
    }

    for (const subStmt of stmt.elseStatements) {
      subStmt.accept(this);
    }
  }

  visitQlQuestion(stmt: QlQuestion): void {
    // nothing to check
  }

  private checkDuplicateIdentifiers(): void {
    if (_.uniqBy(this.allQuestions, 'name').length < this.allQuestions.length) {
      const groupedQuestions = _.groupBy(this.allQuestions, 'name');

      _.forEach(groupedQuestions, (value: QlQuestion[], key: string) => {
        if (value.length > 1) {
          throw new DuplicateIdentifierError(`Duplicate question with identifier ${key}`);
        }
      });
    }
  }

  private setVariableReferences(form: Form): void {
    const allVariables = CollectStatementVariablesVisitor.evaluate(form);

    for (const variable of allVariables) {
      const referencedQuestion = this.allQuestions.find(q => q.name === variable.identifier);
      if (referencedQuestion) {
        variable.referencedQuestion = referencedQuestion;
      } else {
        throw new UnknownQuestionError(`Question with identifier ${variable.identifier} was not found`);
      }
    }
  }
}
