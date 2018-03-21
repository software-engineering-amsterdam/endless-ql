import FieldNodeDecorator from "../../../form/nodes/fields/FieldNodeDecorator";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import MergedFieldStyle from "./MergedFieldStyle";
import SectionNode from "./nodes/containers/SectionNode";
import QuestionStyle from "./nodes/children/QuestionStyle";
import Page from "./nodes/containers/PageNode";

export default class StyledFieldNode extends FieldNodeDecorator {
  private mergedStyle: MergedFieldStyle;
  private section: SectionNode | undefined;
  private styleNode: QuestionStyle | undefined;

  constructor(fieldToBeDecorated: FieldNode, mergedStyle: MergedFieldStyle, questionStyleNode?: QuestionStyle) {
    super(fieldToBeDecorated);
    this.mergedStyle = mergedStyle;
    this.styleNode = questionStyleNode;
  }

  public getMergedStyle(): MergedFieldStyle {
    return this.mergedStyle;
  }

  public getStyleNode() {
    return this.section;
  }

  public getParentSection(): SectionNode | undefined {
    if (!this.styleNode) {
      return undefined;
    }

    return this.styleNode.getNearestParent(parent => parent instanceof SectionNode);
  }

  public getPage(): Page | undefined {
    if (!this.styleNode) {
      return undefined;
    }

    return this.styleNode.getNearestParent(parent => parent instanceof Page);
  }
}