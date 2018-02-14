import Field from "./Field";
import FieldType from "../../field/FieldType";
import NodeVisitor from "../visitors/NodeVisitor";

export default class FieldDecorator implements Field {
  get name(): string {
    return this.fieldToBeDecorated.name;
  }

  get label(): string {
    return this.fieldToBeDecorated.label;
  }

  get type(): FieldType {
    return this.fieldToBeDecorated.type;
  }

  private fieldToBeDecorated: Field;

  constructor(fieldToBeDecorated: Field) {
    this.fieldToBeDecorated = fieldToBeDecorated;
  }

  accept(visitor: NodeVisitor) {
    return this.fieldToBeDecorated.accept(visitor);
  }
}