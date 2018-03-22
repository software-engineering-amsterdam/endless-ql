import StyleAttribute from "../nodes/StyleAttribute";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import ColorAttribute from "../nodes/attributes/base_attributes/ColorAttribute";
import ColorValue from "../values/ColorValue";
import { InvalidColorError, UnknownStyleAttributeNameError } from "../style_errors";
import UnitValue from "../values/UnitValue";
import WidthAttribute from "../nodes/attributes/base_attributes/WidthAttribute";

export default class AttributeNodeFactory {
  public getWidgetStyleAttribute(value: string, options?: string[]): WidgetAttribute {
    return new WidgetAttribute("test");
  }

  public getBaseStyleAttribute(name: string, value: string): StyleAttribute {
    if (name === 'color') {
      return this.getColorAttribute(value);
    }

    if (name === 'width') {
      return new WidthAttribute(this.makePixelUnitValue(parseFloat(value)));
    }

    // TODO: Implement other attributes

    throw UnknownStyleAttributeNameError.make(name);
  }

  private getColorAttribute(value: string): ColorAttribute {
    const color = new ColorValue(value);

    if (!color.isValid()) {
      throw InvalidColorError.make(value);
    }

    return new ColorAttribute(new ColorValue(value));
  }

  public makePixelUnitValue(value: number) {
    return new UnitValue(value, 'px');
  }
}