import TreeNode from "./nodes/TreeNode";
import NodeTraveller from "./nodes/visitors/NodeTraveller";
import VariableIdentifier from "./nodes/expressions/VariableIdentifier";
import FieldNode from "./nodes/fields/FieldNode";
import ComputedField from "./nodes/fields/ComputedField";
import QuestionNode from "./nodes/fields/QuestionNode";

export const filterNodes = (predicate: (node: TreeNode) => boolean, start: TreeNode): any[] => {
  let nodes: TreeNode[] = [];

  const traveller = new NodeTraveller((node: TreeNode) => {
    if (predicate(node)) {
      nodes = nodes.concat([node]);
    }
  });

  start.accept(traveller);
  return nodes;
};

export const getUsedVariables = (start: TreeNode) => {
  const variables: string[] = [];
  const traveller = new NodeTraveller((node: TreeNode) => {
    if (node instanceof VariableIdentifier) {
      variables.push(node.identifier);
    }
  });

  start.accept(traveller);
  return variables;
};

export const isFieldNode = (node: TreeNode): node is FieldNode => {
  return node instanceof QuestionNode || node instanceof ComputedField;
};