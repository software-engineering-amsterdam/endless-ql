import BaseAttribute from "../BaseAttribute";
import UnitValue  from "../../../values/UnitValue";

export default class WidthAttribute extends BaseAttribute {
  private value: UnitValue;

  constructor(value: UnitValue) {
    super();
    this.value = value;
  }

  getName(): string {
    return "width";
  }

  getStringValue(): string {
    return this.value.toString();
  }
}