import Field from "./Field";
import FieldType from "../../field/FieldType";
import NodeVisitor from "../visitors/NodeVisitor";

/**
 * Decorator for Fields that makes the Field "decoratable" for future usage.
 */
export default class FieldDecorator implements Field {
  get identifier(): string {
    return this.fieldToBeDecorated.identifier;
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