import FormNode from "../form/nodes/FormNode";
import { VariableScopeVisitor } from "../form/typechecking/VariableScopeVisitor";
import { VariableInformation } from "../form/VariableIntformation";

const qlParser = require("./parsers/ql_parser");

export interface QlParserResult {
  node: FormNode;
  variables: Map<string, VariableInformation>;
}

export class QlParserPipeline {
  private readonly _qlInput: string;

  constructor(qlInput: string) {
    this._qlInput = qlInput;

    this.processFormNode = this.processFormNode.bind(this);
  }

  run(): QlParserResult[] {
    const formNodes: FormNode[] = qlParser.parse(this._qlInput);

    this.processFormNode(formNodes[0]);

    return formNodes.map(this.processFormNode);
  }

  private processFormNode(node: FormNode): QlParserResult {
    const variableScope: VariableScopeVisitor = new VariableScopeVisitor();
    const scopeResult = variableScope.run(node);

    return {
      node: node,
      variables: scopeResult.variables
    };
  }
}