import FieldNodeDecorator from "../../../form/nodes/fields/FieldNodeDecorator";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import { QuestionStyles } from "./QuestionStyles";
import SectionNode from "./nodes/containers/SectionNode";
import QuestionStyle from "./nodes/children/QuestionStyle";
import Page from "./nodes/containers/Page";

export default class StyledFieldNode extends FieldNodeDecorator {
  private questionStyles: QuestionStyles;
  private section: SectionNode | undefined;
  private questionStyleNode: QuestionStyle | undefined;

  constructor(fieldToBeDecorated: FieldNode, questionStyles: QuestionStyles, questionStyleNode?: QuestionStyle) {
    super(fieldToBeDecorated);
    this.questionStyles = questionStyles;
    this.questionStyleNode = questionStyleNode;
  }

  public getStyles(): QuestionStyles {
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