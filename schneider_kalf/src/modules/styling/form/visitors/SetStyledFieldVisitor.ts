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
import FieldNodeDecorator from "../../../../form/nodes/fields/FieldNodeDecorator";

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
    return null;
  }

  visitComputedField(computedField: ComputedField): any {
    return null;
  }

  visitIfCondition(ifCondition: IfCondition): any {
    ifCondition.then = ifCondition.then.map(this.mapStatement.bind(this));
  }

  visitForm(form: FormNode): any {
    form.statements = form.statements.map(this.mapStatement.bind(this));
  }

  visitFieldDecorator(fieldDecorator: FieldNodeDecorator) {
    return fieldDecorator.getBaseField().accept(this);
  }

  private mapStatement(statement: Statement): Statement {
    if (isFieldNode(statement)) {
      return this.makeStyledFieldNode(statement);
    }

    statement.accept(this);
    return statement;
  }

  private makeStyledFieldNode(field: FieldNode): StyledFieldNode {
    const mergedFieldStyle = this.findFieldStyleOrDefault(field.identifier);
    const fieldStyleNode = this.findFieldStyleNode(field.identifier);

    return new StyledFieldNode(field, mergedFieldStyle, fieldStyleNode);
  }

  private findFieldStyleOrDefault(identifier: string): QuestionStyles {
    const found: QuestionStyles | undefined = this.styles.find(style => style.getIdentifier() === identifier);

    if (!found) {
      return new QuestionStyles(identifier);
    }

    return found;
  }

  private findFieldStyleNode(identifier: string): QuestionStyle | undefined {
    return this.questionStyleNodes.find(styleNode => styleNode.identifier === identifier);
  }
}