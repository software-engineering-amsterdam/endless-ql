import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import Page from "../nodes/containers/PageNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheet";
import MergedFieldStyle from "../MergedFieldStyle";

export default class MergeFieldStylesVisitor implements StyleNodeVisitor {
  private questionStyles: MergedFieldStyle[];

  constructor() {
    this.questionStyles = [];
  }
  getStyles() {
      return this.questionStyles;
  }
  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    return;
  }

  visitQuestionStyle(question: QuestionStyle): any {
    let style = new MergedFieldStyle(question.identifier);
    let parents = question.getParents();

    for (let parent of parents.reverse()) {
      // TODO: add question to check if valid with it's type
      style.inheritStyleFrom(parent);
    }

    style.addLocalStyle(question);
    this.questionStyles.push(style);
    return style;
  }

  visitSection(section: Section): any {
    return section.body.forEach(child => child.accept(this));
  }

  visitPageAttribute(page: Page): any {
    return page.body.forEach(child => child.accept(this));
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