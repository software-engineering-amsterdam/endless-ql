import BaseAttribute from "../BaseAttribute";
import UnitValue  from "../../../values/UnitValue";

export default class WidthAttribute extends BaseAttribute {
  private unitValue: UnitValue;

  constructor(unitValue: UnitValue) {
    super("width", unitValue.toString());
    this.unitValue = unitValue;
  }

  getName(): string {
    return "width";
  }

  getStringValue(): string {
    return this.unitValue.toString();
  }
}