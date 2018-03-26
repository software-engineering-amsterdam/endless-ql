import StyleNodeVisitor from "./StyleNodeVisitor";
import DefaultStyle from "../nodes/children/DefaultStyleNode";
import QuestionStyle from "../nodes/children/QuestionStyle";
import Section from "../nodes/containers/SectionNode";
import Page from "../nodes/containers/PageNode";
import WidgetAttribute from "../nodes/attributes/WidgetAttribute";
import BaseAttribute from "../nodes/attributes/BaseAttribute";
import StyleSheetNode from "../nodes/StyleSheetNode";
import MergedFieldStyle from "../MergedFieldStyle";
import { VariableInformation } from "../../../../form/VariableIntformation";
import { type } from "os";
import StyleTreeNode from "../nodes/StyleTreeNode";
import { getDefaultStyleNodes } from "../style_helpers";
import { UnkownQuestionUsedInLayoutError } from "../style_errors";

export default class MergeFieldStylesVisitor implements StyleNodeVisitor {
  private questionStyles: MergedFieldStyle[];
  private qlVariables: Map<string, VariableInformation>;

  constructor(qlVariables: Map<string, VariableInformation>) {
    this.qlVariables = qlVariables;
    this.questionStyles = [];
  }

  getMergedStyles() {
    return this.questionStyles;
  }

  visitDefaultStyle(defaultStyle: DefaultStyle): any {
    return;
  }

  visitQuestionStyle(question: QuestionStyle): any {
    const variableInformation: VariableInformation | undefined = this.qlVariables.get(question.identifier);

    if (typeof variableInformation === 'undefined') {
      throw UnkownQuestionUsedInLayoutError.make(question.identifier);
    }

    let mergedStyle = new MergedFieldStyle(question.identifier, variableInformation.type);
    let parents: StyleTreeNode[] = question.getParents();

    for (let parent of parents.reverse()) {
      mergedStyle.applyDefaults(getDefaultStyleNodes(parent));
    }

    mergedStyle.applyStyle(question.children);

    this.questionStyles.push(mergedStyle);
    return mergedStyle;
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