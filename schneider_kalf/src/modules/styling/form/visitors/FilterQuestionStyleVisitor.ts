import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/Section";
import Page from "../nodes/containers/Page";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheet";

export default class QuestionStylesVisitor implements StyleNodeVisitor {
  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    return;
  }

  visitQuestionStyle(question: QuestionStyle): any {
    return new Error("not implemented yet");
  }

  visitSection(section: Section): any {
    return section.body.forEach(child => child.accept(this));
  }

  visitPageAttribute(page: Page): any {
    return;
  }

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any {
    return;
  }

  visitBaseAttribute(baseAttribute: BaseAttribute): any {
    return;
  }

  visitStyleSheet(stylesheet: Stylesheet): any {
    return stylesheet.children.forEach(child => child.accept(this));
  }
}