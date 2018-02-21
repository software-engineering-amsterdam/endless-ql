import ExpressionVisitor from "../visitors/ExpressionVisitor";
import Expression from "./Expression";

export default class VariableIdentifier implements Expression {
  readonly identifier: string;

  /**
   * Created a variable identifier that occurs inside a formula expression.
   * @param {string} identifier
   */
  constructor(identifier: string) {
    this.identifier = identifier;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitVariableIdentifier(this);
  }
}