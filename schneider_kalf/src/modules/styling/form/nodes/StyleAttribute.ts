import StyleTreeNode from "./StyleTreeNode";

export default interface StyleAttribute extends StyleTreeNode {
  readonly name: string;
  readonly value: string;
}
