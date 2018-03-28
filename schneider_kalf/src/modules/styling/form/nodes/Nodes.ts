import StyleSheetNode from "./StyleSheetNode";
import BaseAttribute from "./attributes/BaseAttribute";
import Page from "./containers/PageNode";
import Section from "./containers/SectionNode";
import WidgetAttribute from "./attributes/WidgetAttribute";
import QuestionStyle from "./children/QuestionStyle";
import DefaultStyle from "./children/DefaultStyleNode";
import AttributeNodeFactory from "../factories/AttributeNodeFactory";
import DropdownWidgetAttribute from "./attributes/widget_attribtues/DropdownWidgetAttribute";
import SliderWidgetAttribute from "./attributes/widget_attribtues/SliderWidgetAttribute";
import CheckboxWidgetAttribute from "./attributes/widget_attribtues/CheckboxWidgetAttribute";
import BooleanWidgetAttribute from "./attributes/widget_attribtues/BooleanWidgetAttribute";
import RadioWidgetAttribute from "./attributes/widget_attribtues/RadioWidgetAttribute";
import SpinBoxWidgetAttribute from "./attributes/widget_attribtues/SpinBoxWidgetAttribute";
import TextWidgetAttribute from "./attributes/widget_attribtues/TextWidgetAttribute";

/**
 * List all available node types for easy access in the grammar.
 * This list is not needed otherwise, but used to create according
 * instances inside the parser.
 */
export default {
  Stylesheet: StyleSheetNode,
  BaseAttribute,
  WidgetAttribute,
  Page,
  Section,
  QuestionStyle,
  DefaultStyle,
  AttributeNodeFactory,
  DropdownWidgetAttribute,
  CheckboxWidgetAttribute,
  SliderWidgetAttribute,
  BooleanWidgetAttribute,
  RadioWidgetAttribute,
  SpinBoxWidgetAttribute,
  TextWidgetAttribute
};
