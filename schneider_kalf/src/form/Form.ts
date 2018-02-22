import FieldNode from "./nodes/fields/FieldNode";
import FormState from "./state/FormState";

export default interface Form {
  getName(): string;

  getFields(): FieldNode[];

  getState(): FormState;

  setAnswer(identifier: string, value: any): Form;

  getAnswer(identifier: string): any;
}