import FieldNodeDecorator from "../../../form/nodes/fields/FieldNodeDecorator";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import MergedFieldStyle from "./MergedFieldStyle";
import SectionNode from "./nodes/containers/SectionNode";
import QuestionStyle from "./nodes/children/QuestionStyle";
import Page from "./nodes/containers/PageNode";

export default class StyledFieldNode extends FieldNodeDecorator {
  private questionStyles: MergedFieldStyle;
  private section: SectionNode | undefined;
  private questionStyleNode: QuestionStyle | undefined;

  constructor(fieldToBeDecorated: FieldNode, questionStyles: MergedFieldStyle, questionStyleNode?: QuestionStyle) {
    super(fieldToBeDecorated);
    this.questionStyles = questionStyles;
    this.questionStyleNode = questionStyleNode;
  }

  public getStyles(): MergedFieldStyle {
    return this.questionStyles;
  }

  public getStyleNode() {
    return this.section;
  }

  public getParentSection(): SectionNode | undefined {
    if (!this.questionStyleNode) {
      return undefined;
    }

    return this.questionStyleNode.getNearestParent(parent => parent instanceof SectionNode);
  }

  public getPage(): Page | undefined {
    if (!this.questionStyleNode) {
      return undefined;
    }

    return this.questionStyleNode.getNearestParent(parent => parent instanceof Page);
  }
}