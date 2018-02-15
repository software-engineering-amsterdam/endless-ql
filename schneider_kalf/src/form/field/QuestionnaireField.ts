import Field from "./Field";
import FieldType from "./FieldType";
import ValueWrapper from "../values/ValueWrapper";

export class QuestionnaireField implements Field {
  readonly label: string;
  readonly name: string;
  readonly value: ValueWrapper;
  readonly type: FieldType;

  constructor(props: { label: string; name: string; value: ValueWrapper; type: FieldType }) {
    this.label = props.label;
    this.name = props.name;
    this.value = props.value;
    this.type = props.type;
  }
}