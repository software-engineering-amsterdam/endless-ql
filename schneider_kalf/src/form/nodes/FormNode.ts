import Statement from "./Statement";
import TreeNode from "./TreeNode";
import NodeVisitor from "./visitors/NodeVisitor";

export default class FormNode implements TreeNode {
  get statements(): Statement[] {
    return this._statements;
  }

  get name(): string {
    return this._name;
  }

  private _name: string;

  private _statements: Statement[];

  constructor(name: string, statements: Statement[]) {
    this._name = name;
    this._statements = statements;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitForm(this);
  }
}