import Form from "./Form";
import Field from "./field/Field";

export default class QuestionnaireForm implements Form {
  readonly fields: Field[];
  readonly name: string;

  constructor(name: string, fields: Field[]) {
    this.name = name;
    this.fields = fields;
  }
}