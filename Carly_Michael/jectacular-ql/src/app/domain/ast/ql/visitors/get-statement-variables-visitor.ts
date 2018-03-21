import {StatementVisitor} from './statement-visitor';
import {Variable} from '../';
import {ExpressionQuestion} from '../expression-question';
import {Form} from '../form';
import {If} from '../if';
import {QlQuestion} from '../ql-question';
import {Statement} from '../statement';
import {GetExpressionVariablesVisitor} from './get-expression-variables-visitor';

export class GetStatementVariablesVisitor implements StatementVisitor<void> {
  private variables: Variable[];
  private constructor() {
    this.variables = [];
  }

  static evaluate(stmt: Statement): ReadonlyArray<Variable> {
    const visitor = new GetStatementVariablesVisitor();
    stmt.accept(visitor);
    return visitor.variables;
  }

  visitExpressionQuestion(stmt: ExpressionQuestion): void {
    this.variables = this.variables.concat(GetExpressionVariablesVisitor.evaluate(stmt.expression));
  }

  visitForm(stmt: Form): void {
    for (const statement of stmt.statements) {
      statement.accept(this);
    }
  }

  visitIf(stmt: If): void {
    for (const statement of stmt.statements) {
      statement.accept(this);
    }

    for (const statement of stmt.elseStatements) {
      statement.accept(this);
    }

    this.variables = this.variables.concat(GetExpressionVariablesVisitor.evaluate(stmt.condition));
  }

  visitQlQuestion(stmt: QlQuestion): void {
    // nothing to do
  }

}
