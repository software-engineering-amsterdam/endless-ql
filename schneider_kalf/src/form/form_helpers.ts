import EvaluationVisitor from "./nodes/visitors/EvaluationVisitor";
import Expression from "./nodes/expressions/Expression";
import TreeNode from "./nodes/TreeNode";
import NodeTraveller from "./nodes/visitors/NodeTraveller";

/**
 * Alias to evaluate a form using the EvaluationVisitor
 *
 * @param {Expression} expression
 * @returns {any}
 */
export const evaluate = (expression: Expression): any => {
  const evaluationVisitor = new EvaluationVisitor();
  return expression.accept(evaluationVisitor);
};

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

export const findNode = (predicate: (node: TreeNode) => boolean, start: TreeNode): (TreeNode | null) => {
  const found = filterNodes(predicate, start);

  if (found.length === 0) {
    return null;
  }

  return found[0];
};