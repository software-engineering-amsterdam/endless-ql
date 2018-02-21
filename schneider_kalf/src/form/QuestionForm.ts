import Form from "./Form";
import FormNode from "./nodes/FormNode";
import FieldNode from "./nodes/fields/FieldNode";
import { filterNodes } from "./form_helpers";
import FormState from "./state/FormState";
import ComputedField from "./nodes/fields/ComputedField";
import Question from "./nodes/fields/Question";
import Maybe = jest.Maybe;
import { UnkownFieldError } from "./form_errors";

export default class QuestionForm implements Form {
  private node: FormNode;
  private state: FormState;

  constructor(formNode: FormNode, state: FormState) {
    this.node = formNode;
    this.state = state;
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

  findField(identifier: string): Maybe<FieldNode> {
    return this.getFields().find((field: FieldNode) => {
      return field.identifier === identifier;
    });
  }

  getName(): string {
    return this.node.name;
  }

  getState(): FormState {
    return this.state;
  }

  setAnswer(identifier: string, value: any): Form {
    return new QuestionForm(this.node, this.state.set(identifier, value));
  }

  getAnswer(identifier: string) {
    const field = this.findField(identifier);

    if (!field) {
      throw UnkownFieldError.make(identifier);
    }

    try {
      return field.getAnswer(this.state);
    } catch (e) {
      console.error(e);
      return null;
    }
  }
}