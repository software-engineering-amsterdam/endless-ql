import BinaryExpression from "../BinaryExpression";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";

export default class Multiplication extends BinaryExpression {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitMultiplication(this);
  }
}