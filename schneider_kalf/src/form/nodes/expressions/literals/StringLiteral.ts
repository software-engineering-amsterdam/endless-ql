import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";
import AbstractTreeNode from "../../AbstractTreeNode";

export default class StringLiteral extends AbstractTreeNode implements Expression {
  private value: string;

  constructor(value: string) {
    super();
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitStringLiteral(this);
  }

  getValue(): string {
    return this.value;
  }
}