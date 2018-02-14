import ExpressionVisitor from "../visitors/ExpressionVisitor";
import Expression from "./Expression";

export default class Variable implements Expression {
  readonly identifier: string;

  constructor(identifier: string) {
    this.identifier = identifier;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitVariable(this);
  }
}