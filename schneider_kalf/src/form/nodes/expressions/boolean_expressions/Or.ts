import BinaryExpression from "../BinaryExpression";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";

export default class Or extends BinaryExpression {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitOr();
  }
}