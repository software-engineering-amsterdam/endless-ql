import StyleSheetNode from "./StyleSheetNode";
import BaseAttribute from "./attributes/BaseAttribute";
import Page from "./containers/PageNode";
import Section from "./containers/SectionNode";
import WidgetAttribute from "./attributes/WidgetAttribute";
import QuestionStyle from "./children/QuestionStyle";
import DefaultStyle from "./children/DefaultStyleNode";

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
  DefaultStyle
};