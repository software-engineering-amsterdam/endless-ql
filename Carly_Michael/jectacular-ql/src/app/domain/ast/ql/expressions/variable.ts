import {Expression} from './expression';
import {QlQuestion} from '../ql-question';
import {Location} from '../../location';
import {ExpressionVisitor} from '../visitors/expression-visitor';

export class Variable extends Expression {
  public referencedQuestion: QlQuestion;

  constructor(public identifier: string, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return [this];
  }

  accept<T>(visitor: ExpressionVisitor<T>): T {
    return visitor.visitVariable(this);
  }
}
