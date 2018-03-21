import StyleTreeNode from "./StyleTreeNode";

export default interface StyleAttribute extends StyleTreeNode {
  getName(): string;

  getStringValue(): string;
}