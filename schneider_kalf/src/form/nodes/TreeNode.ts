import NodeVisitor from "./visitors/NodeVisitor";

/**
 * Basic behaviour of a syntax tree node that accepts a tree node visitor.
 */
interface TreeNode {
  accept(visitor: NodeVisitor): any;
}

export default TreeNode;