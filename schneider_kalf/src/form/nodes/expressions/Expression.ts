import TreeNode from "../TreeNode";
import ExpressionVisitor from "../visitors/FieldVisitor";

export default class Expression implements TreeNode {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitExpression(this);
  }
}
