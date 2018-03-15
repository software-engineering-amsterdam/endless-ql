import StyleTreeNode from "./StyleTreeNode";
import StyleNodeVisitor from "../visitors/StyleNodeVisitor";

export default abstract class AbstractStyleNode implements StyleTreeNode {
  private parent: StyleTreeNode;

  getParent(): StyleTreeNode | null {
    return this.parent;
  }

  getParents(): StyleTreeNode[] {
    const parents: StyleTreeNode[] = [];
    let parent: StyleTreeNode | null = this.parent;

    while (parent !== null) {
      parents.push(parent);
      parent = parent.getParent();
    }

    return parents;
  }

  setParent(parent: StyleTreeNode): void {
    this.parent = parent;
  }

  abstract accept(visitor: StyleNodeVisitor);

}