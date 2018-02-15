import FieldType from "../../field/FieldType";
import Field from "./Field";
import Expression from "../expressions/Expression";
import NodeVisitor from "../visitors/NodeVisitor";

export default class ComputedField implements Field {
  readonly label: string;
  readonly identifier: string;
  readonly type: FieldType;
  readonly formula: Expression;

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