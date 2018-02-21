import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";

export default class BooleanLiteral implements Expression {
  private value: boolean;

  constructor(value: boolean) {
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitBooleanLiteral(this);
  }

  getValue() {
    return this.value;
  }
}