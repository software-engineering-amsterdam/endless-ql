import TreeNode from "../TreeNode";
import ExpressionVisitor from "../visitors/ExpressionVisitor";

export default abstract class Expression implements TreeNode {
  abstract accept(visitor: ExpressionVisitor): any;
}
