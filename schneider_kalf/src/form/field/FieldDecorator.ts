import Field from "./Field";
import ValueWrapper from "../values/ValueWrapper";
import FieldType from "./FieldType";

export default class FieldDecorator implements Field {
  get name(): string {
    return this.fieldToBeDecorated.name;
  }

  get label(): string {
    return this.fieldToBeDecorated.label;
  }

  get value(): ValueWrapper {
    return this.fieldToBeDecorated.value;
  }

  get type(): FieldType {
    return this.fieldToBeDecorated.type;
  }

  private fieldToBeDecorated: Field;

  constructor(fieldToBeDecorated: Field) {
    this.fieldToBeDecorated = fieldToBeDecorated;
  }

}