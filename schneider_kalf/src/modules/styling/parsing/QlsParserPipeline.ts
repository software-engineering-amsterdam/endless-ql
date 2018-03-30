import { getQlsParser } from "./parsing_helpers";
import StyleSheet from "../form/nodes/StyleSheetNode";
import SetParentsVisitor from "../form/visitors/SetParentsVisitor";
import MergeFieldStylesVisitor from "../form/visitors/MergeFieldStylesVisitor";
import MergedFieldStyle from "../form/MergedFieldStyle";
import { QlParserPipeline, QlParserResult } from "../../../parsing/QlParserPipeline";
import TypeCheckVisitor from "../form/visitors/TypeCheckVisitor";
import { VariablesMap } from "../../../form/type_checking/VariableScopeVisitor";
import SourceText from "../../../form/source/SourceText";

export interface QlsParserResult extends QlParserResult {
  styleNode: StyleSheet;
  styles: MergedFieldStyle[];
}

export class QlsParserPipeline {
  private readonly qlsInput: SourceText;
  private readonly qlInput: SourceText;

  constructor(qlInput: SourceText, qlsInput: SourceText) {
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

    return {
      node: qlParserResult.node,
      variables: qlParserResult.variables,
      styleNode: styleSheetNode,
      styles: styles
    };
  }

  private getQuestionStyles(node: StyleSheet, qlVariables: VariablesMap) {
    return MergeFieldStylesVisitor.run(node, qlVariables);
  }

  private setNodeParents(node: StyleSheet): void {
    const parentsVisitor: SetParentsVisitor = new SetParentsVisitor();
    node.accept(parentsVisitor);
  }

  private checkTypes(node: StyleSheet, qlVariables: VariablesMap): void {
    const typeCheckQlsVisitor: TypeCheckVisitor = new TypeCheckVisitor(qlVariables);
    node.accept(typeCheckQlsVisitor);
  }

  private parseQl(qlInput: SourceText): QlParserResult {
    const qlPipeline = new QlParserPipeline(qlInput);
    return qlPipeline.runFirst();
  }

  private parseQls(qlsInput: SourceText): StyleSheet {
    return getQlsParser().parse(qlsInput.toString());
  }
}