import Form from "./Form";
import Field from "./field/Field";

/**
 * Questionnaire form that contains fields and is given a name.
 * Is used to hold information about the form described in the
 * DSL input.
 */
export default class QuestionnaireForm implements Form {
  readonly fields: Field[];
  readonly name: string;

  constructor(name: string, fields: Field[]) {
    this.name = name;
    this.fields = fields;
  }
}