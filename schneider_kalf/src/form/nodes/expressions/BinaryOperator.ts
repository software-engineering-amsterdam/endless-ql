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

  /**
   * Abstract binary operator that contains a left and a right side for further
   * evaluation. Used to extract shared behaviour of an Addition, Subtraction, Equals, etc.
   * @param {Expression} left
   * @param {Expression} right
   */
  constructor(left: Expression, right: Expression) {
    this._left = left;
    this._right = right;
  }

  abstract accept(visitor: ExpressionVisitor): any;
}