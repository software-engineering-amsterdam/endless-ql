import StyleAttribute from "../nodes/StyleAttribute";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import ColorAttribute from "../nodes/attributes/base_attributes/ColorAttribute";
import ColorValue from "../values/ColorValue";
import { InvalidColorError, UnknownStyleAttributeNameError } from "../style_errors";
import UnitValue from "../values/UnitValue";
import WidthAttribute from "../nodes/attributes/base_attributes/WidthAttribute";

export default class AttributeNodeFactory {
  public getWidgetStyleAttribute(value: string, options?: string[]): WidgetAttribute {

  }

  public getBaseStyleAttribute(name: string, value: string): StyleAttribute {
    if (name === 'color') {
      const color = new ColorValue(value);

      if (!color.isValid()) {
        throw InvalidColorError.make(value);
      }

      return new ColorAttribute(new ColorValue(value));
    }

    if (name === 'width') {
      return new WidthAttribute(this.makePixelUnitValue(parseFloat(value)));
    }

    // TODO: Implement other attributes

    throw UnknownStyleAttributeNameError.make(name);
  }

  public makePixelUnitValue(value: number) {
    return new UnitValue(value, 'px');
  }
}