import NodeVisitor from "./visitors/NodeVisitor";

interface TreeNode {
  accept(visitor: NodeVisitor): any;
}

export default TreeNode;