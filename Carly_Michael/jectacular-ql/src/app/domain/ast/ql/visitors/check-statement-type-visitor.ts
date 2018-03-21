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
import {GetStatementVariablesVisitor} from './get-statement-variables-visitor';
import {GetExpressionVariablesVisitor} from './get-expression-variables-visitor';

export class CheckStatementTypeVisitor implements StatementVisitor<void> {
  private constructor(readonly allQuestions: QlQuestion[]) { }

  static evaluate(allQuestions: QlQuestion[], stmt: Statement): void {
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
    this.checkDuplicateIdentifiers(this.allQuestions);
    this.setVariableReferences(this.allQuestions, stmt);

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
    const variables = GetExpressionVariablesVisitor.evaluate(stmt.condition);
    const questions = stmt.getQuestions();

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

  private checkDuplicateIdentifiers(allQuestions: QlQuestion[]): void {
    if (_.uniqBy(allQuestions, 'name').length < allQuestions.length) {
      const groupedQuestions = _.groupBy(allQuestions, 'name');

      _.forEach(groupedQuestions, (value: QlQuestion[], key: string) => {
        if (value.length > 1) {
          throw new DuplicateIdentifierError(`Duplicate question with identifier ${key}`);
        }
      });
    }
  }

  private setVariableReferences(allQuestions: QlQuestion[], form: Form): void {
    const allVariables = GetStatementVariablesVisitor.evaluate(form);

    for (const variable of allVariables) {
      const referencedQuestion = allQuestions.find(q => q.name === variable.identifier);
      if (referencedQuestion) {
        variable.referencedQuestion = referencedQuestion;
      } else {
        throw new UnknownQuestionError(`Question with identifier ${variable.identifier} was not found`);
      }
    }
  }
}
