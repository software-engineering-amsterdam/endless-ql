import FieldType from "../../field/FieldType";
import Field from "./Field";
import NodeVisitor from "../visitors/NodeVisitor";

export default class Question implements Field {
  readonly label: string;
  readonly name: string;
  readonly type: FieldType;

  constructor(name: string, label: string, type: FieldType) {
    this.label = label;
    this.name = name;
    this.type = type;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitQuestion(this);
  }
}