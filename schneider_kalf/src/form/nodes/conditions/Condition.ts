import TreeNode from "../TreeNode";
import Statement from "../Statement";
import NodeVisitor from "../visitors/NodeVisitor";
import Expression from "../expressions/Expression";
import AbstractTreeNode from "../AbstractTreeNode";

export default abstract class Condition extends AbstractTreeNode implements TreeNode {
  set then(value: Statement[]) {
    this._then = value;
  }

  get then(): Statement[] {
    return this._then;
  }

  get otherwise(): Statement[] {
    if (this._otherwise === undefined) {
      return [];
    }
    return this._otherwise;
  }

  get predicate(): Expression {
    return this._predicate;
  }

  private _predicate: Expression;
  private _then: Statement[];
  private _otherwise: Statement[] | undefined;

  constructor(predicate: Expression, then: Statement[], otherwise?: Statement[]) {
    super();
    this._predicate = predicate;
    this._then = then;
    this._otherwise = otherwise;
  }

  abstract accept(visitor: NodeVisitor): any;
}