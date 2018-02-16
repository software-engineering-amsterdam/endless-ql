import Field from "./field/Field";

export default interface Form {
  readonly name: string;
  readonly fields: Field[];
}