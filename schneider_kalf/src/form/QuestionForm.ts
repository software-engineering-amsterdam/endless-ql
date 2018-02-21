import Form from "./Form";
import FormNode from "./nodes/FormNode";
import FieldNode from "./nodes/fields/FieldNode";
import { filterNodes } from "./form_helpers";
import FormState from "./state/FormState";
import ComputedField from "./nodes/fields/ComputedField";
import Question from "./nodes/fields/Question";

export default class QuestionForm implements Form {
  private node: FormNode;

  constructor(formNode: FormNode, state: FormState) {
    this.node = formNode;
  }

  getFields(): FieldNode[] {
    return this.getComputedFields().concat(this.getQuestions());
  }

  getComputedFields(): ComputedField[] {
    return filterNodes((node) => node instanceof ComputedField, this.node);
  }

  getQuestions(): ComputedField[] {
    return filterNodes((node) => node instanceof Question, this.node);
  }
}