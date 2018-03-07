import StyleNodeVisitor from "../visitors/StyleNodeVisitor";

export default interface StyleTreeNode {
  accept(visitor: StyleNodeVisitor);
}