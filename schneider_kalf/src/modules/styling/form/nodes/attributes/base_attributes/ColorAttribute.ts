import BaseAttribute from "../BaseAttribute";
import ColorValue from "../../../values/ColorValue";

export default class ColorAttribute extends BaseAttribute {
  private value: ColorValue;

  constructor(value: ColorValue) {
    super();
    this.value = value;
  }

  getName(): string {
    return "color";
  }

  getStringValue(): string {
    return this.value.toString();
  }
}