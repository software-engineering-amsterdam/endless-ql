import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import Page from "../nodes/containers/PageNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheet";
import { checkBaseAttribute, checkWidgetAttribute } from "../style_helpers";

export default class TypeCheckVisitor implements StyleNodeVisitor {
  private identifiersQL: Map<string, any>;

  constructor(identifiersQL: Map<string, any>) {
    this.identifiersQL = identifiersQL;
  }

  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    // TODO: ASK: Added extends StyleTreeNode to StyleAttribute
    return defaultStyle.children.forEach(child => child.accept(this));
  }

  visitQuestionStyle(question: QuestionStyle): any {
    if (!this.identifiersQL.get[question.identifier]) {
      // Warning("Question '" + question.identifier + "' does not exist in the QL template")
    }
    return question.children.forEach(child => child.accept(this));
  }

  visitSection(section: Section): any {
    return section.body.forEach(child => child.accept(this));
  }

  visitPageAttribute(page: Page): any {
    return page.body.forEach(child => child.accept(this));
  }

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any {
    checkWidgetAttribute(widgetAttribute);
    return;
  }

  visitBaseAttribute(baseAttribute: BaseAttribute): any {
    checkBaseAttribute(baseAttribute.name, baseAttribute.value);
    return;
  }

  visitStyleSheet(stylesheet: Stylesheet): any {
    return stylesheet.children.forEach(child => child.accept(this));
  }
}