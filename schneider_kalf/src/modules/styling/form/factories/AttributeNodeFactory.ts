import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import ColorAttribute from "../nodes/attributes/base_attributes/ColorAttribute";
import { UnknownStyleAttributeNameError } from "../style_errors";
import WidthAttribute from "../nodes/attributes/base_attributes/WidthAttribute";
import TextAttribute from "../nodes/attributes/widget_attribtues/TextAttribute";
import SliderAttribute from "../nodes/attributes/widget_attribtues/SliderAttribute";
import DropdownAttribute from "../nodes/attributes/widget_attribtues/DropdownAttribute";
import CheckboxAttribute from "../nodes/attributes/widget_attribtues/CheckboxAttribute";
import FontSizeAttribute from "../nodes/attributes/base_attributes/FontSizeAttribute";
import SpinboxAttribute from "../nodes/attributes/widget_attribtues/SpinBoxAttribute";
import FontAttribute from "../nodes/attributes/base_attributes/FontAttribute";
import RadioAttribute from "../nodes/attributes/widget_attribtues/RadioAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";

export default class AttributeNodeFactory {
  public getWidgetStyleAttribute(value: string, options?: string[]): WidgetAttribute | undefined {

    if (value === 'spinbox') {
      return new SpinboxAttribute(options);
    }
    if (value === 'text') {
      return new TextAttribute(options);
    }
    if (value === 'slider') {
      return new SliderAttribute(options);
    }
    if (value === 'dropdown') {
      return new DropdownAttribute(options);
    }
    if (value === 'checkbox') {
      return new CheckboxAttribute(options);
    }
    if (value === 'radio') {
      return new RadioAttribute(options);
    }

    throw UnknownStyleAttributeNameError.make(name);
  }

  public getBaseStyleAttribute(name: string, value: string): BaseAttribute {
    if (name === 'color') {
      return ColorAttribute.makeFromString(value);
    }
    if (name === 'width') {
      return new WidthAttribute(value);
    }
    if (name === 'fontsize') {
      return new FontSizeAttribute(value);
    }
    if (name === 'font') {
      return new FontAttribute(value);
    }

    throw UnknownStyleAttributeNameError.make(name);
  }
}