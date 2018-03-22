import FieldVisitor from "../../../../form/nodes/visitors/FieldVisitor";
import QuestionNode from "../../../../form/nodes/fields/QuestionNode";
import ComputedField from "../../../../form/nodes/fields/ComputedField";
import IfCondition from "../../../../form/nodes/conditions/IfCondition";
import FormNode from "../../../../form/nodes/FormNode";
import StyleSheet, { default as Stylesheet } from "../nodes/StyleSheetNode";
import { isFieldNode } from "../../../../form/form_helpers";
import StyledFieldNode from "../StyledFieldNode";
import FieldNode from "../../../../form/nodes/fields/FieldNode";
import Statement from "../../../../form/nodes/Statement";
import MergedFieldStyle from "../MergedFieldStyle";
import { getQuestionStyleNodes } from "../style_helpers";
import QuestionStyle from "../nodes/children/QuestionStyle";
import FieldNodeDecorator from "../../../../form/nodes/fields/FieldNodeDecorator";
import { FieldType } from "../../../../form/FieldType";

export default class SetStyledFieldVisitor implements FieldVisitor {
  private readonly styles: MergedFieldStyle[];
  private readonly styleNode: Stylesheet;
  private readonly questionStyleNodes: QuestionStyle[];

  constructor(styles: MergedFieldStyle[], styleNode: StyleSheet) {
    this.styles = styles;
    this.styleNode = styleNode;
    this.questionStyleNodes = getQuestionStyleNodes(styleNode, true);
  }

  visitQuestion(question: QuestionNode): any {
    return null;
  }

  visitComputedField(computedField: ComputedField): any {
    return null;
  }

  visitIfCondition(ifCondition: IfCondition): any {
    ifCondition.then = ifCondition.then.map(this.mapStatement.bind(this));
    ifCondition.otherwise = ifCondition.otherwise.map(this.mapStatement.bind(this));
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
    const mergedFieldStyle = this.findMergedStyleOrEmpty(field.identifier, field.type);
    const fieldStyleNode = this.findFieldStyleNode(field.identifier);

    return new StyledFieldNode(field, mergedFieldStyle, fieldStyleNode);
  }

  private findMergedStyleOrEmpty(identifier: string, type: FieldType): MergedFieldStyle {
    const found: MergedFieldStyle | undefined = this.styles.find(style => style.getIdentifier() === identifier);

    if (!found) {
      return new MergedFieldStyle(identifier, type);
    }

    return found;
  }

  private findFieldStyleNode(identifier: string): QuestionStyle | undefined {
    return this.questionStyleNodes.find(styleNode => styleNode.identifier === identifier);
  }
}