import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";

export default class StringLiteral implements Expression {
  private value: string;

  constructor(value: string) {
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitStringLiteral(this);
  }

  getValue(): string {
    return this.value;
  }
}