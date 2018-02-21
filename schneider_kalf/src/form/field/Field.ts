import FieldType from "./FieldType";
import ValueWrapper from "../values/ValueWrapper";

export default interface Field {
  readonly name: string;
  readonly label: string;
  readonly value: ValueWrapper;
  readonly type: FieldType;
}