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

    // Can it actually be null? got error that stylesheet parent is undefined
    while (parent !== null) {

      // TODO: Refactor so this isn't needed
      if (parent === undefined) { break; }

      parents.push(parent);
      parent = parent.getParent();
    }

    return parents;
  }

  setParent(parent: StyleTreeNode): void {
    this.parent = parent;
  }

  setStyle() {
    return;
  }

  abstract accept(visitor: StyleNodeVisitor);

}