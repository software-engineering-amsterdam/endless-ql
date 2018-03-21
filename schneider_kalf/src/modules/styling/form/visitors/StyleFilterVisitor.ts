import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyle";
import Page from "../nodes/containers/PageNode";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheet";
import StyleTreeNode from "../nodes/StyleTreeNode";

const defaults: StyleFilterOptions = {
  includeDefaults: true,
  includeQuestions: true,
  recursive: false,
};

interface StyleFilterOptions {
  includeDefaults: boolean;
  includeQuestions: boolean;
  recursive: boolean;
}

export default class StyleFilterVisitor implements StyleNodeVisitor {
  private options: StyleFilterOptions;
  private isInitial = true;

  constructor(options: StyleFilterOptions) {
    this.options = Object.assign({}, defaults, options);
  }

  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    return (this.options.includeDefaults) ? [defaultStyle] : [];
  }

  visitQuestionStyle(question: QuestionStyle): any {
    return (this.options.includeQuestions) ? [question] : [];
  }

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any {
    return [];
  }

  visitBaseAttribute(baseAttribute: BaseAttribute): any {
    return [];
  }

  visitSection(section: Section): any {
    return this.visitChildren(section.body);
  }

  visitPageAttribute(page: Page): any {
    return this.visitChildren(page.body);
  }

  visitStyleSheet(stylesheet: Stylesheet): any {
    return this.visitChildren(stylesheet.children);
  }

  // TODO: Either use body or children so we can use this general function
  private visitChildren(children: StyleTreeNode[]) {
    if (this.isInitial === false && this.options.recursive === false) {
      return [];
    }

    this.isInitial = false;
    return children.reduce(
        (filtered: StyleTreeNode[], child: StyleTreeNode) => filtered.concat(child.accept(this)),
        []
    );
  }
}