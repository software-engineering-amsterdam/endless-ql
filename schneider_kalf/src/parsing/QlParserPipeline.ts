import FormNode from "../form/nodes/FormNode";
import { VariableScopeResult, VariableScopeVisitor, VariablesMap } from "../form/type_checking/VariableScopeVisitor";
import { VariableInformation } from "../form/VariableIntformation";
import { TypeCheckVisitor } from "../form/type_checking/TypeCheckVisitor";
import { getQlParser } from "./parsing_helpers";

export interface QlParserResult {
  node: FormNode;
  variables: Map<string, VariableInformation>;
}

export class QlParserPipeline {
  private readonly qlInput: string;

  constructor(qlInput: string) {
    this.qlInput = qlInput;
  }

  run(): QlParserResult[] {
    const formNodes: FormNode[] = getQlParser().parse(this.qlInput);
    return formNodes.map((node) => this.processFormNode(node));
  }

  private processFormNode(node: FormNode): QlParserResult {
    const scope = this.checkScope(node);
    this.checkTypes(node, scope.variables);

    return {
      node: node,
      variables: scope.variables
    };
  }

  private checkScope(node: FormNode): VariableScopeResult {
    const scopeVisitor: VariableScopeVisitor = new VariableScopeVisitor();
    return scopeVisitor.run(node);
  }

  private checkTypes(node: FormNode, variables: VariablesMap): void {
    const typeCheckVisitor = new TypeCheckVisitor(variables);
    node.accept(typeCheckVisitor);
  }
}