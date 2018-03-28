import FormNode from "../form/nodes/FormNode";
import { VariableScopeResult, VariableScopeVisitor, VariablesMap } from "../form/type_checking/VariableScopeVisitor";
import { VariableInformation } from "../form/VariableIntformation";
import { TypeCheckVisitor } from "../form/type_checking/TypeCheckVisitor";
import { getQlParser } from "./parsing_helpers";
import SourceText from "../form/source/SourceText";
import { NeedAtLeastOneFormToParseError } from "../form/form_errors";

export interface QlParserResult {
  node: FormNode;
  variables: Map<string, VariableInformation>;
}

export class QlParserPipeline {
  private readonly qlInput: SourceText;

  constructor(qlInput: SourceText) {
    this.qlInput = qlInput;
  }

  run(): QlParserResult[] {
    return this.getFormNodes().map((node) => this.processFormNode(node));
  }

  runFirst(): QlParserResult {
    const nodes: FormNode[] = this.getFormNodes();

    if (nodes.length === 0) {
      throw NeedAtLeastOneFormToParseError.make();
    }

    return this.processFormNode(nodes[0]);
  }

  private processFormNode(node: FormNode): QlParserResult {
    const scope = this.checkScope(node);
    this.checkTypes(node, scope.variables);

    return {
      node: node,
      variables: scope.variables
    };
  }

  private getFormNodes(): FormNode[] {
    return getQlParser().parse(this.qlInput.toString());
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