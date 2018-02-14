import NodeTypes from "./NodeTypes";

export interface SyntaxTree {
  id: 1;
  type: NodeTypes.Form;
  attributes: {
    name: string
  };
  children: Node[];
}

export interface Node {
  id: number;
  type: NodeTypes;
  name?: string;
  attributes?: { [key: string]: AttributeValue };
  children?: Node[];
}

export type AttributeValue = string | number;
