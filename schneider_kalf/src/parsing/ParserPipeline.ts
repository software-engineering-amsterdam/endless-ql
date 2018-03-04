import FormNode from "../form/nodes/FormNode";
import { VariableScopeVisitor } from "../form/typechecking/VariableScopeVisitor";

const qlParser = require("./parsing/parsers/ql_parser");

export default class ParserPipeline {
  private readonly _qlInput: string;

  constructor(qlInput: string) {
    this._qlInput = qlInput;

    this.processFormNode = this.processFormNode.bind(this);
  }

  run() {
    const formNodes: FormNode[] = qlParser.parse(this._qlInput);

    this.processFormNode(formNodes[0]);

    return formNodes.map(this.processFormNode);
  }

  processFormNode(node: FormNode) {
    const variableScope: VariableScopeVisitor = new VariableScopeVisitor();

    variableScope.run(node);

    return {
      node: node
    };
  }
}