import FieldVisitor from "../../../../form/nodes/visitors/FieldVisitor";
import Question from "../../../../form/nodes/fields/Question";
import ComputedField from "../../../../form/nodes/fields/ComputedField";
import IfCondition from "../../../../form/nodes/conditions/IfCondition";
import FormNode from "../../../../form/nodes/FormNode";
import StyleSheet, { default as Stylesheet } from "../nodes/StyleSheet";
import { isFieldNode } from "../../../../form/form_helpers";
import StyledFieldNode from "../StyledFieldNode";
import FieldNode from "../../../../form/nodes/fields/FieldNode";
import Statement from "../../../../form/nodes/Statement";
import { QuestionStyles } from "../QuestionStyles";
import { getQuestionStyleNodes } from "../style_helpers";
import QuestionStyle from "../nodes/children/QuestionStyle";

export default class SetStyledFieldVisitor implements FieldVisitor {
  private readonly styles: QuestionStyles[];
  private readonly styleNode: Stylesheet;
  private readonly questionStyleNodes: QuestionStyle[];

  constructor(styles: QuestionStyles[], styleNode: StyleSheet) {
    this.styles = styles;
    this.styleNode = styleNode;
    this.questionStyleNodes = getQuestionStyleNodes(styleNode, true);
  }

  visitQuestion(question: Question): any {
    return question;
  }

  visitComputedField(computedField: ComputedField): any {
    return computedField;
  }

  visitIfCondition(ifCondition: IfCondition): any {
    ifCondition.then.map(this.mapStatement.bind(this));
  }

  visitForm(form: FormNode): any {
    form.statements.map(this.mapStatement.bind(this));
  }

  private mapStatement(statement: Statement): Statement {
    if (isFieldNode(statement)) {
      return this.makeStyledFieldNode(statement);
    }

    return statement;
  }

  private makeStyledFieldNode(field: FieldNode): StyledFieldNode {
    const fieldStyle = this.findQuestionStyleOrDefault(field.identifier);
    return new StyledFieldNode(field, fieldStyle);
  }

  private findQuestionStyleOrDefault(identifier: string): QuestionStyles {
    const found: QuestionStyles | undefined = this.styles.find(style => style.getIdentifier() === identifier);

    if (!found) {
      return new QuestionStyles(identifier);
    }

    return found;
  }
}