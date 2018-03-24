import BaseAttribute from "../BaseAttribute";
import ColorValue from "../../../values/ColorValue";
import { InvalidColorError } from "../../../style_errors";

export default class ColorAttribute extends BaseAttribute {
  private color: ColorValue;

  static makeFromString(value: string) {
    const color = new ColorValue(value);
    if (!color.isValid()) {
      throw InvalidColorError.make(value);
    }
    return new ColorAttribute(color);
  }

  constructor(color: ColorValue) {
    super();
    this.color = color;
  }

  getCssValues(): object {
    return {
      [this.getName()] : this.getStringValue(),
    };
  }

  getName(): string {
    return "color";
  }

  getStringValue(): string {
    return this.color.toString();
  }

}