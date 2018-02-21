import FieldType from "../../FieldType";
import Field from "./FieldNode";
import Expression from "../expressions/Expression";
import NodeVisitor from "../visitors/NodeVisitor";

export default class ComputedField implements Field {
  readonly label: string;
  readonly identifier: string;
  readonly type: FieldType;
  readonly formula: Expression;

  /**
   * Creates a computed field that will be rendered as a readonly label that
   * displays the result of the provided formula.
   *
   * @param {string} identifier
   * @param {string} label
   * @param {FieldType} type
   * @param {Expression} formula
   */
  constructor(identifier: string, label: string, type: FieldType, formula: Expression) {
    this.label = label;
    this.identifier = identifier;
    this.type = type;
    this.formula = formula;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitComputedField(this);
  }
}