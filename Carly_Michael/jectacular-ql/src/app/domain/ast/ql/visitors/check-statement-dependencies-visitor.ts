import {QlQuestion} from '../ql-question';
import {Statement} from '../statement';
import {StatementVisitor} from './statement-visitor';
import {ExpressionQuestion} from '../expression-question';
import {Form} from '../form';
import {If} from '../if';
import {CollectExpressionVariablesVisitor} from './collect-expression-variables-visitor';
import {CircularDependencyError} from '../../../errors';
import * as _ from 'lodash';

export class CheckStatementDependenciesVisitor implements StatementVisitor<void> {
  private constructor() {
  }

  static evaluate(statement: Statement): void {
    const visitor = new CheckStatementDependenciesVisitor();
    statement.accept(visitor);
  }

  visitExpressionQuestion(statement: ExpressionQuestion): void {
    const variables = CollectExpressionVariablesVisitor.evaluate(statement.expression);
    const circularDependency = _.find(variables, ['identifier', statement.name]);
    if (circularDependency) {
      throw new CircularDependencyError(`The expression of question ${statement.name} references to itself`);
    }
  }

  visitForm(statement: Form): void {
    for (const subStatement of statement.statements) {
      subStatement.accept(this);
    }
  }

  visitIf(statement: If): void {
    for (const subStatement of statement.statements) {
      subStatement.accept(this);
    }

    for (const subStatement of statement.elseStatements) {
      subStatement.accept(this);
    }
  }

  visitQlQuestion(stmt: QlQuestion): void {
    // nothing to check
  }
}
