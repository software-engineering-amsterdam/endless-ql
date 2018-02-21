import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";

export default class NumberLiteral implements Expression {
  private value: number;

  constructor(value: number) {
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitNumberLiteral(this);
  }

  getValue() {
    return this.value;
  }
}