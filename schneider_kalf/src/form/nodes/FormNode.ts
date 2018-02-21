import Statement from "./Statement";
import TreeNode from "./TreeNode";
import NodeVisitor from "./visitors/NodeVisitor";

export default class FormNode implements TreeNode {
  private _name: string;

  private _statements: Statement[];

  /**
   * The root node of a form which contains statements (fields or conditions)
   * and is given a name.
   * @param {string} name
   * @param {Statement[]} statements
   */
  constructor(name: string, statements: Statement[]) {
    this._name = name;
    this._statements = statements;
  }

  /**
   * List of fields and conditions that implement the Statement interface.
   * @returns {Statement[]}
   */
  get statements(): Statement[] {
    return this._statements;
  }

  /**
   * Name of the form
   * @returns {string}
   */
  get name(): string {
    return this._name;
  }

  /**
   * Accept a node visitor for calculations or other actions.
   * @param {NodeVisitor} visitor
   * @returns {any}
   */
  accept(visitor: NodeVisitor): any {
    return visitor.visitForm(this);
  }
}