import StyleTreeNode from "./StyleTreeNode";
import StyleNodeVisitor from "../visitors/StyleNodeVisitor";

export default abstract class AbstractStyleNode implements StyleTreeNode {
  abstract accept(visitor: StyleNodeVisitor);
}