import Form from "./Form";
import FormNode from "./nodes/FormNode";
import FieldNode from "./nodes/fields/FieldNode";
import { filterNodes } from "./form_helpers";
import FormState from "./state/FormState";
import ComputedField from "./nodes/fields/ComputedField";
import QuestionNode from "./nodes/fields/QuestionNode";
import Maybe = jest.Maybe;
import { UnkownDefaultValueError, UnkownFieldError } from "./form_errors";
import FieldVisitor from "./nodes/visitors/FieldVisitor";
import defaultValues from "./defaultValues";

export default class QlForm implements Form {
  private node: FormNode;
  private state: FormState;

  constructor(formNode: FormNode, state: FormState) {
    this.node = formNode;
    this.state = state;

    this.fillDefaultValues();
    this.computeFields();
  }

  getField(identifier: string): FieldNode | undefined | any {
    return this.getFields().find(field => field.identifier === identifier);
  }

  getFields(): FieldNode[] {
    return filterNodes((node) => node instanceof ComputedField || node instanceof QuestionNode, this.node);
  }

  computeFields() {
    let state: FormState = this.state;

    this.getComputedFields().forEach((field: ComputedField) => {
      state = state.set(field.identifier, field.computeAnswer(state));
    });

    this.state = state;
  }

  fillDefaultValues() {
    let state: FormState = this.state;

    this.getQuestions().forEach((field: QuestionNode) => {
      if (state.has(field.identifier)) {
        return;
      }

      if (!defaultValues.has(field.type)) {
        throw UnkownDefaultValueError.make(field.type);
      }

      state = state.set(field.identifier, defaultValues.get(field.type));
    });

    this.state = state;
  }

  getComputedFields(): ComputedField[] {
    return filterNodes((node) => node instanceof ComputedField, this.node);
  }

  getQuestions(): QuestionNode[] {
    return filterNodes((node) => node instanceof QuestionNode, this.node);
  }

  findField(identifier: string): Maybe<FieldNode> {
    return this.getFields().find((field: FieldNode) => {
      return field.identifier === identifier;
    });
  }

  getName(): string {
    return this.node.name;
  }

  getState(): FormState | any {
    return this.state;
  }

  setAnswer(identifier: string, value: any): Form {
    return this.setState(this.state.set(identifier, value));
  }

  setState(nextState: FormState): Form {
    return new QlForm(this.node, nextState);
  }

  getRootNode(): FormNode {
    return this.node;
  }

  getAnswer(identifier: string) {
    const field = this.findField(identifier);

    if (!field) {
      throw UnkownFieldError.make(identifier);
    }

    return this.state.get(identifier);
  }

  accept(visitor: FieldVisitor) {
    return this.node.accept(visitor);
  }
}