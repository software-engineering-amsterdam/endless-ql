export default interface StyleTreeNode {
  accept(visitor: StyleNodeVisitor);
}