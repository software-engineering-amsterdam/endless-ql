import FieldNodeDecorator from "../../../form/nodes/fields/FieldNodeDecorator";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import MergedFieldStyle from "./MergedFieldStyle";
import QuestionStyle from "./nodes/children/QuestionStyle";
import Page, { default as PageNode } from "./nodes/containers/PageNode";

export default class StyledFieldNode extends FieldNodeDecorator {
  private mergedStyle: MergedFieldStyle;
  private styleNode: QuestionStyle | undefined;

  constructor(fieldToBeDecorated: FieldNode, mergedStyle: MergedFieldStyle, questionStyleNode?: QuestionStyle) {
    super(fieldToBeDecorated);
    this.mergedStyle = mergedStyle;
    this.styleNode = questionStyleNode;
  }

  public getMergedStyle(): MergedFieldStyle {
    return this.mergedStyle;
  }

  public getPage(): Page | undefined {
    if (!this.styleNode) {
      return undefined;
    }

    return this.styleNode.getNearestParent(parent => parent instanceof Page);
  }

  public isOnPage(otherPage?: PageNode): boolean {
    const page = this.getPage();

    if (!page || !otherPage) {
      return false;
    }

    return page.isEqual(otherPage);
  }
}