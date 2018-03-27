import { getQlsParser } from "./parsing_helpers";
import StyleSheet from "../form/nodes/StyleSheetNode";
import SetParentsVisitor from "../form/visitors/SetParentsVisitor";
import QuestionStylesVisitor from "../form/visitors/MergeFieldStylesVisitor";
import MergedFieldStyle from "../form/MergedFieldStyle";
import { QlParserPipeline, QlParserResult } from "../../../parsing/QlParserPipeline";
import SetStyledFieldVisitor from "../form/visitors/SetStyledFieldVisitor";
import { VariableInformation } from "../../../form/VariableIntformation";
import TypeCheckVisitor from "../form/visitors/TypeCheckVisitor";
import { VariablesMap } from "../../../form/type_checking/VariableScopeVisitor";
import FormNode from "../../../form/nodes/FormNode";

export interface QlsParserResult extends QlParserResult {
  styleNode: StyleSheet;
  styles: MergedFieldStyle[];
}

export class QlsParserPipeline {
  private readonly qlsInput: string;
  private readonly qlInput: string;

  constructor(qlInput: string, qlsInput: string) {
    this.qlInput = qlInput;
    this.qlsInput = qlsInput;
  }

  run(): QlsParserResult {
    const qlParserResult = this.parseQl(this.qlInput);
    const styleSheetNode: StyleSheet = this.parseQls(this.qlsInput);

    const variablesMap: VariablesMap = qlParserResult.variables;

    this.checkTypes(styleSheetNode, variablesMap);
    this.setNodeParents(styleSheetNode);
    const styles: MergedFieldStyle[] = this.getQuestionStyles(styleSheetNode, variablesMap);
    this.addStyleToQlFieldNodes(styles, styleSheetNode, qlParserResult.node);

    return {
      node: qlParserResult.node,
      variables: qlParserResult.variables,
      styleNode: styleSheetNode,
      styles: styles
    };
  }

  private addStyleToQlFieldNodes(styles: MergedFieldStyle[], styleSheetNode: StyleSheet, qlFormNode: FormNode) {
    const setStyledField = new SetStyledFieldVisitor(styles, styleSheetNode);
    qlFormNode.accept(setStyledField);
  }

  private getQuestionStyles(node: StyleSheet, qlVariables: VariablesMap) {
    const styleVisitor = new QuestionStylesVisitor(qlVariables);
    node.accept(styleVisitor);
    return styleVisitor.getMergedStyles();
  }

  private setNodeParents(node: StyleSheet): void {
    const parentsVisitor: SetParentsVisitor = new SetParentsVisitor();
    node.accept(parentsVisitor);
  }

  private checkTypes(node: StyleSheet, qlVariables: VariablesMap): void {
    const typeCheckQlsVisitor: TypeCheckVisitor = new TypeCheckVisitor(qlVariables);
    node.accept(typeCheckQlsVisitor);
  }

  private parseQl(qlInput: string): QlParserResult {
    const qlPipeline = new QlParserPipeline(qlInput);
    return qlPipeline.run()[0];
  }

  private parseQls(qlsInput: string): StyleSheet {
    return getQlsParser().parse(qlsInput);
  }
}