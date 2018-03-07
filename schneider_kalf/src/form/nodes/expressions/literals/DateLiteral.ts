import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";
import AbstractTreeNode from "../../AbstractTreeNode";

export default class DateLiteral extends AbstractTreeNode implements Expression {
  private value: any;

  constructor(value: any) {
    super();
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitDateLiteral(this);
  }

  getValue(): string {
    return this.value;
  }
}