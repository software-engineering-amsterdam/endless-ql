import Form from "./Form";
import Field from "./field/Field";
import Condition from "./condition/Condition";

export default class QuestionnaireForm implements Form {
  readonly fields: Field[];
  readonly conditions: Condition[];
  readonly name: string;

  constructor(name: string, fields: Field[], conditions: Condition[]) {
    this.name = name;
    this.fields = fields;
    this.conditions = conditions;
  }
}