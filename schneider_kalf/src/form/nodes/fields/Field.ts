import FieldType from "../../field/FieldType";
import Statement from "../Statement";

export default interface Field extends Statement {
  readonly name: string;
  readonly label: string;
  readonly type: FieldType;
}