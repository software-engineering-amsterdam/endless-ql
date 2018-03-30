import ExpressionVisitor from "../visitors/ExpressionVisitor";
import AbstractLiteral from "./AbstractLiteral";

export default class DateLiteral extends AbstractLiteral {
  private value: Date;

  constructor(value: Date) {
    super();
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitDateLiteral(this);
  }

  getValue(): Date {
    return this.value;
  }
}