import {ExpressionQuestion} from '../expression-question';
import {Form} from '../form';
import {If} from '../if';
import {QlQuestion} from '../ql-question';

export interface StatementVisitor<T> {
  visitExpressionQuestion(stmt: ExpressionQuestion): T;

  visitForm(stmt: Form): T;

  visitIf(stmt: If): T;

  visitQlQuestion(stmt: QlQuestion): T;
}
