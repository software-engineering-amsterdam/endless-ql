import Form from "../../../form/Form";
import FormNode from "../../../form/nodes/FormNode";
import PagedFormState from "./PagedFormState";
import FieldNode from "../../../form/nodes/fields/FieldNode";
import StyleSheetNode from "../form/nodes/StyleSheet";
import QuestionForm from "../../../form/QuestionForm";
import FieldVisitor from "../../../form/nodes/visitors/FieldVisitor";

export default class StyledForm implements Form {
  private baseForm: QuestionForm;
  private stylesheetNode: StyleSheetNode;

  constructor(formNode: FormNode, state: PagedFormState, stylesheetNode: StyleSheetNode) {
    this.stylesheetNode = stylesheetNode;
    this.baseForm = new QuestionForm(formNode, state);
  }

  getName(): string {
    return this.baseForm.getName();
  }

  getFields(): FieldNode[] {
    return this.baseForm.getFields();
  }

  getState(): PagedFormState {
    return this.baseForm.getState();
  }

  setAnswer(identifier: string, value: any): Form {
    return this.baseForm.setAnswer(identifier, value);
  }

  getAnswer(identifier: string): any {
    return this.baseForm.getAnswer(identifier);
  }

  accept(visitor: FieldVisitor): any {
    return this.baseForm.accept(visitor);
  }
}