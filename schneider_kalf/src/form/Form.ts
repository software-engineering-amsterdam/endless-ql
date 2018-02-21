import FieldNode from "./nodes/fields/FieldNode";

export default interface Form {
  getFields(): FieldNode[];
}