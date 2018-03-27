import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import ColorAttribute from "../nodes/attributes/base_attributes/ColorAttribute";
import { UnknownStyleAttributeNameError } from "../style_errors";
import WidthAttribute from "../nodes/attributes/base_attributes/WidthAttribute";
import FontSizeAttribute from "../nodes/attributes/base_attributes/FontSizeAttribute";
import FontAttribute from "../nodes/attributes/base_attributes/FontAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import CheckboxWidgetAttribute from "../nodes/attributes/widget_attribtues/CheckboxWidgetAttribute";
import TextWidgetAttribute from "../nodes/attributes/widget_attribtues/TextWidgetAttribute";
import SliderWidgetAttribute from "../nodes/attributes/widget_attribtues/SliderWidgetAttribute";
import DropdownWidgetAttribute from "../nodes/attributes/widget_attribtues/DropdownWidgetAttribute";
import RadioWidgetAttribute from "../nodes/attributes/widget_attribtues/RadioWidgetAttribute";
import SpinBoxWidgetAttribute from "../nodes/attributes/widget_attribtues/SpinBoxWidgetAttribute";

export default class AttributeNodeFactory {
  public getWidgetStyleAttribute(value: string, options?: string[]): WidgetAttribute | undefined {

    if (value === 'spinbox') {
      return new SpinBoxWidgetAttribute(options);
    }
    if (value === 'text') {
      return new TextWidgetAttribute(options);
    }
    if (value === 'slider') {
      return new SliderWidgetAttribute(options);
    }
    if (value === 'dropdown') {
      return new DropdownWidgetAttribute(options);
    }
    if (value === 'checkbox') {
      return new CheckboxWidgetAttribute(options);
    }
    if (value === 'radio') {
      return new RadioWidgetAttribute(options);
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