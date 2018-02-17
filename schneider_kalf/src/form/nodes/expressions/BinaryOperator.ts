import Expression from "./Expression";
import ExpressionVisitor from "../visitors/ExpressionVisitor";

export default abstract class BinaryOperator implements Expression {
  get right(): Expression {
    return this._right;
  }

  get left(): Expression {
    return this._left;
  }

  private _left: Expression;
  private _right: Expression;

  constructor(left: Expression, right: Expression) {
    this._left = left;
    this._right = right;
  }

  abstract accept(visitor: ExpressionVisitor): any;
}