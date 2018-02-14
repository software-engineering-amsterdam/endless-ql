import BinaryOperator from "../BinaryOperator";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";

export default class NotEquals extends BinaryOperator {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitNotEquals(this);
  }
}