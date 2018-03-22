import {Statement} from '../statement';
import {StatementVisitor} from './statement-visitor';
import {QlQuestion} from '../ql-question';
import {ExpressionQuestion} from '../expression-question';
import {Form} from '../form';
import {If} from '../if';

export class CollectQuestionsVisitor implements StatementVisitor<void> {
  private questions: QlQuestion[];

  private constructor() {
    this.questions = [];
  }

  static evaluate(stmt: Statement): ReadonlyArray<QlQuestion> {
    const visitor = new CollectQuestionsVisitor();
    stmt.accept(visitor);
    return visitor.questions;
  }

  visitExpressionQuestion(stmt: ExpressionQuestion): void {
    this.questions.push(stmt);
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
  }

  visitQlQuestion(stmt: QlQuestion): void {
    this.questions.push(stmt);
  }



}
