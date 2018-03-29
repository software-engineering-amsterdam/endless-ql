import GenericCollection from "../../../../form/collection/GenericCollection";
import VariableIdentifier from "../../../../form/nodes/expressions/VariableIdentifier";

export default class IdentifierNodeCollection extends GenericCollection<VariableIdentifier> {
  getIdentifierNames(): string[] {
    return this.toArray().map(identifierNode => identifierNode.identifier);
  }
}