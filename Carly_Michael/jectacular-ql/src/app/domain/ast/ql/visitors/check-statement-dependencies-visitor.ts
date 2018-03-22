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

  static evaluate(stmt: Statement): void {
    const visitor = new CheckStatementDependenciesVisitor();
    stmt.accept(visitor);
  }

  visitExpressionQuestion(stmt: ExpressionQuestion): void {
    const variables = CollectExpressionVariablesVisitor.evaluate(stmt.expression);
    const circularDependency = _.find(variables, ['identifier', stmt.name]);
    if (circularDependency) {
      throw new CircularDependencyError(`The expression of question ${stmt.name} references to itself`);
    }
  }

  visitForm(stmt: Form): void {
    for (const subStmt of stmt.statements) {
      subStmt.accept(this);
    }
  }

  visitIf(stmt: If): void {
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
}
