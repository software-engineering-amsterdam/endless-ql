import StatefulForm from "./StatefulForm";
import FormNode from "./nodes/FormNode";
import FieldNode from "./nodes/fields/FieldNode";
import { filterNodes } from "./form_helpers";
import FormState from "./state/FormState";
import ComputedField from "./nodes/fields/ComputedFieldNode";
import QuestionNode from "./nodes/fields/QuestionNode";
import Maybe = jest.Maybe;
import { UnkownDefaultValueError, UnkownFieldError } from "./form_errors";
import FieldVisitor from "./nodes/visitors/FieldVisitor";
import defaultValues from "./defaultValues";
import { VariableScopeVisitor, VariablesMap } from "./type_checking/VariableScopeVisitor";

export default class QlForm implements StatefulForm {
  private node: FormNode;
  private state: FormState;

  constructor(formNode: FormNode, state: FormState) {
    this.node = formNode;
    this.state = state;

    this.fillDefaultValues();
    this.computeFields();
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

  getField(identifier: string): FieldNode | undefined | any {
    return this.getFields().find(field => field.identifier === identifier);
  }

  getFields(): FieldNode[] {
    return filterNodes((node) => node instanceof ComputedField || node instanceof QuestionNode, this.node);
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

  setAnswer(identifier: string, value: any): StatefulForm {
    return this.setState(this.state.set(identifier, value));
  }

  setState(nextState: FormState): StatefulForm {
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

  getVariablesMap(): VariablesMap {
    return VariableScopeVisitor.run(this.node).variables;
  }
}