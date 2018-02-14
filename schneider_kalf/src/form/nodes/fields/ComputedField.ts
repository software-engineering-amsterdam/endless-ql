import FieldType from "../../field/FieldType";
import Field from "./Field";
import Expression from "../expressions/Expression";
import NodeVisitor from "../visitors/NodeVisitor";

export default class ComputedField implements Field {
  readonly label: string;
  readonly name: string;
  readonly type: FieldType;
  readonly formula: Expression;

  constructor(name: string, label: string, type: FieldType, formula: Expression) {
    this.label = label;
    this.name = name;
    this.type = type;
    this.formula = formula;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitComputedField(this);
  }
}