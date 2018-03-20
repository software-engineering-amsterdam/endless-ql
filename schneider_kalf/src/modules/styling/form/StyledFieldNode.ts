import FieldNodeDecorator from "../../../form/nodes/fields/FieldNodeDecorator";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import { QuestionStyles } from "./QuestionStyles";

export default class StyledFieldNode extends FieldNodeDecorator {
  private questionStyles: QuestionStyles;

  constructor(fieldToBeDecorated: FieldNode, questionStyles: QuestionStyles) {
    super(fieldToBeDecorated);
    this.questionStyles = questionStyles;
  }

  public getStyles(): QuestionStyles {
    return this.questionStyles;
  }
}