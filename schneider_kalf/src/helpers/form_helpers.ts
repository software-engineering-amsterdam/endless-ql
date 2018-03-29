import TreeNode from "../form/nodes/TreeNode";
import FormNodeTraversingVisitor from "../form/nodes/visitors/FormNodeTraversingVisitor";
import IdentifierNodeCollection from "../modules/styling/form/collections/IdentifierNodeCollection";

export const getUsedVariableIdentifiers = (start: TreeNode): string[] => {
  const variables: IdentifierNodeCollection = FormNodeTraversingVisitor.collectUsedVariables(start);
  return variables.getIdentifierNames();
};