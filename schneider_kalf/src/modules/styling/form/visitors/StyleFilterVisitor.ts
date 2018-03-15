import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import Page from "../nodes/containers/Page";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/Section";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheet";
import StyleTreeNode from "../nodes/StyleTreeNode";

const defaults: StyleFilterOptions = {
  includeDefaults: true,
  includeQuestions: true
};

interface StyleFilterOptions {
  includeDefaults: boolean;
  includeQuestions: boolean;
}

export default class StyleFilterVisitor implements StyleNodeVisitor {
  private options: StyleFilterOptions;
  private isInitial = true;

  constructor(options: StyleFilterOptions) {
    this.options = Object.assign({}, defaults, options);
  }

  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    return this.options.includeDefaults;
  }

  visitQuestionStyle(question: QuestionStyle): any {
    return this.options.includeQuestions;
  }

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any {
    return;
  }

  visitBaseAttribute(baseAttribute: BaseAttribute): any {
    return;
  }

  visitSection(section: Section): any {
    if (!this.isInitial) {
      return false;
    }

    this.isInitial = false;
    return section.body.filter(child => child.accept(this));
  }

  visitPageAttribute(page: Page): any {
    if (!this.isInitial) {
      return false;
    }

    this.isInitial = false;
    return page.body.filter(child => child.accept(this));
  }

  visitStyleSheet(stylesheet: Stylesheet): any {
    if (!this.isInitial) {
      return false;
    }

    this.isInitial = false;
    return stylesheet.children.filter(child => child.accept(this));
  }
  // TODO: Either use body or children so we can use this general function
  private visitChildren(children: StyleTreeNode[]) {
    if (!this.isInitial) {
      return false;
    }

    this.isInitial = false;
    return children.filter(child => child.accept(this));
  }
}