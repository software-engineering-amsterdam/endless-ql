import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyleNode";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import Page from "../nodes/containers/PageNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import Stylesheet from "../nodes/StyleSheetNode";
import { QuestionPlacedTwiceInLayoutError, UnkownQuestionUsedInLayoutError } from "../style_errors";

export default class TypeCheckVisitor implements StyleNodeVisitor {
  private qlVariables: Map<string, any>;
  private allQuestions: Map<string, QuestionStyle>;

  constructor(qlVariables: Map<string, any>) {
    this.qlVariables = qlVariables;
    this.allQuestions = new Map();
  }

  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    // TODO: ASK: Added extends StyleTreeNode to StyleAttribute
    defaultStyle.children.forEach(child => child.accept(this));
  }

  visitQuestionStyle(question: QuestionStyle): any {
    const duplicateQuestion: QuestionStyle | undefined = this.allQuestions.get(question.identifier);

    if (typeof duplicateQuestion !== 'undefined') {
      throw QuestionPlacedTwiceInLayoutError.make(question, duplicateQuestion);
    }

    this.allQuestions.set(question.identifier, question);

    if (this.qlVariables.has[question.identifier] === false) {
      throw UnkownQuestionUsedInLayoutError.make(question.identifier);
    }

    question.children.forEach(child => child.accept(this));
  }

  visitSection(section: Section): any {
    section.body.forEach(child => child.accept(this));
  }

  visitPageAttribute(page: Page): any {
    page.body.forEach(child => child.accept(this));
  }

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any {
    widgetAttribute.validate();
  }

  visitBaseAttribute(baseAttribute: BaseAttribute): any {
    // checkBaseAttribute(baseAttribute.getName(), baseAttribute.getStringValue());
  }

  visitStyleSheet(stylesheet: Stylesheet): any {
    stylesheet.children.forEach(child => child.accept(this));
  }
}