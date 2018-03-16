import { getQlsParser } from "./parsing_helpers";
import StyleSheet from "../form/nodes/StyleSheet";
import { VariableInformation } from "../../../form/VariableIntformation";
import SetParentsVisitor from "../form/visitors/SetParentsVisitor";
import QuestionStylesVisitor from "../form/visitors/QuestionStylesVisitor";
import { QuestionStyles } from "../form/QuestionStyles";

export interface QlsParserResult {
  node: StyleSheet;
  styles: QuestionStyles[];
}

export class QlsParserPipeline {
  private readonly _qlsInput: string;

  constructor(qlsInput: string) {
    this._qlsInput = qlsInput;

    this.processStylesheetNode = this.processStylesheetNode.bind(this);
  }

  run(): QlsParserResult {
    const styleNode: StyleSheet = getQlsParser().parse(this._qlsInput);
    return this.processStylesheetNode(styleNode);
  }

  private processStylesheetNode(node: StyleSheet): QlsParserResult {
    const parentsVisitor: SetParentsVisitor = new SetParentsVisitor();
    node.accept(parentsVisitor);

    // Why does node.accept(styleVisitor) returns undefined?
    const styleVisitor = new QuestionStylesVisitor();
    node.accept(styleVisitor);
    const result = styleVisitor.getStyles();

    return {
      node: node,
      styles: result
    };
  }
}