import { getQlsParser } from "./parsing_helpers";
import StyleSheet from "../form/nodes/StyleSheetNode";
import SetParentsVisitor from "../form/visitors/SetParentsVisitor";
import QuestionStylesVisitor from "../form/visitors/MergeFieldStylesVisitor";
import MergedFieldStyle from "../form/MergedFieldStyle";
import { QlParserPipeline, QlParserResult } from "../../../parsing/QlParserPipeline";
import SetStyledFieldVisitor from "../form/visitors/SetStyledFieldVisitor";
import { VariableInformation } from "../../../form/VariableIntformation";

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

    this.processStylesheetNode = this.processStylesheetNode.bind(this);
  }

  run(): QlsParserResult {
    const qlPipeline = new QlParserPipeline(this.qlInput);
    const qlPipelineResult = qlPipeline.run()[0];

    const styleNode: StyleSheet = getQlsParser().parse(this.qlsInput);

    const stylesheetResult = this.processStylesheetNode(styleNode, qlPipelineResult.variables);

    const setStyledField = new SetStyledFieldVisitor(stylesheetResult.styles, stylesheetResult.styleNode);
    qlPipelineResult.node.accept(setStyledField);

    return {
      node: qlPipelineResult.node,
      variables: qlPipelineResult.variables,
      styleNode: stylesheetResult.styleNode,
      styles: stylesheetResult.styles
    };
  }

  private processStylesheetNode(node: StyleSheet, qlVariables: Map<string, VariableInformation> ) {
    const parentsVisitor: SetParentsVisitor = new SetParentsVisitor();
    node.accept(parentsVisitor);

    // Why does node.accept(styleVisitor) returns undefined?
    const styleVisitor = new QuestionStylesVisitor(qlVariables);
    node.accept(styleVisitor);
    const result = styleVisitor.getStyles();

    return {
      styleNode: node,
      styles: result
    };
  }
}