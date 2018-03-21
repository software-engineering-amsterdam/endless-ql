import FieldNode from "./nodes/fields/FieldNode";
import FormState from "./state/FormState";
import FieldVisitor from "./nodes/visitors/FieldVisitor";
import FormNode from "./nodes/FormNode";

export default interface Form {
  getRootNode(): FormNode;

  getName(): string;

  getFields(): FieldNode[];

  getField(identifier: string): FieldNode | undefined | any;

  getState(): FormState | any;

  setState(state: FormState): Form;

  setAnswer(identifier: string, value: any): Form;

  getAnswer(identifier: string): any;

  accept(visitor: FieldVisitor): any;
}