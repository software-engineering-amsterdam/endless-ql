import BaseAttribute from "../BaseAttribute";
import ColorValue from "../../../values/ColorValue";

export default class ColorAttribute extends BaseAttribute {
  private color: ColorValue;

  constructor(value: ColorValue) {
    super("color", value.toString());
    this.color = value;
  }

  getName(): string {
    return "color";
  }

  getStringValue(): string {
    return this.color.toString();
  }
}