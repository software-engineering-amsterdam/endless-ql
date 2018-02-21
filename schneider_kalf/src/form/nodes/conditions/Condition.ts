import TreeNode from "../TreeNode";
import Statement from "../Statement";
import NodeVisitor from "../visitors/NodeVisitor";
import Expression from "../expressions/Expression";

export default abstract class Condition implements TreeNode {
  get then(): Statement[] {
    return this._then;
  }

  get predicate(): Expression {
    return this._predicate;
  }

  private _predicate: Expression;
  private _then: Statement[];

  constructor(predicate: Expression, then: Statement[]) {
    this._predicate = predicate;
    this._then = then;
  }

  abstract accept(visitor: NodeVisitor): any;
}