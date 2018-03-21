import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import Page from "../nodes/containers/PageNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import StyleSheetNode from "../nodes/StyleSheetNode";
import MergedFieldStyle from "../MergedFieldStyle";
import { VariableInformation } from "../../../../form/VariableIntformation";

export default class MergeFieldStylesVisitor implements StyleNodeVisitor {
  private questionStyles: MergedFieldStyle[];
  private qlVariables: Map<string, VariableInformation>;

  constructor(qlVariables: Map<string, VariableInformation>) {
    this.qlVariables = qlVariables;
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
      style.inheritStyleFrom(parent, this.qlVariables);
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

  visitStyleSheet(stylesheet: StyleSheetNode): any {
    return stylesheet.children.forEach(child => child.accept(this));
  }
}