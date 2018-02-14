import BinaryExpression from "../BinaryExpression";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";

export default class Addition extends BinaryExpression {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitAddition(this);
  }
}