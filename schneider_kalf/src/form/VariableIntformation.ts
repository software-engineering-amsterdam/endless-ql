import FieldNode from "./nodes/fields/FieldNode";
import { FieldType } from "./FieldType";

export type VariablesInformation = Map<string, VariableInformation>;

export interface VariableInformation {
  identifier: string;
  fieldNode: FieldNode;
  type: FieldType;
  location?: any;
}

export const getVariableInformation = (fieldNode: FieldNode): VariableInformation => {
  return {
    identifier: fieldNode.identifier,
    fieldNode: fieldNode,
    type: fieldNode.type,
    location: null
  };
};