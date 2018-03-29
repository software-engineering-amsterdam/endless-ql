import StatefulForm from "../../../form/StatefulForm";
import PagedFormState from "./PagedFormState";
import StyleSheetNode from "./nodes/StyleSheetNode";
import FieldVisitor from "../../../form/nodes/visitors/FieldVisitor";
import PageNode from "./nodes/containers/PageNode";
import FormNode from "../../../form/nodes/FormNode";
import FormState from "../../../form/state/FormState";
import MergedFieldStyle from "./MergedFieldStyle";
import MergeFieldStylesVisitor from "./visitors/MergeFieldStylesVisitor";
import { VariablesMap } from "../../../form/type_checking/VariableScopeVisitor";
import { getQuestionStyleNodes } from "./style_helpers";
import QuestionStyle from "./nodes/children/QuestionStyle";
import StyledField from "./StyledField";
import FieldNode from "../../../form/nodes/fields/FieldNode";

export default class QlsForm implements StatefulForm {
  private baseForm: StatefulForm;
  private stylesheetNode: StyleSheetNode;
  private mergedStyles: MergedFieldStyle[];  // TODO: Replace this with MergedFieldStyleCollection
  private questionStyles: QuestionStyle[]; // TODO: Replace with QuestionStyleCollection

  constructor(baseForm: StatefulForm, stylesheetNode: StyleSheetNode) {
    this.stylesheetNode = stylesheetNode;
    this.baseForm = baseForm;
    this.mergedStyles = MergeFieldStylesVisitor.run(stylesheetNode, this.getVariablesMap());
    this.questionStyles = getQuestionStyleNodes(stylesheetNode, true);
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

  setAnswer(identifier: string, value: any): StatefulForm {
    const newBaseForm = this.baseForm.setAnswer(identifier, value);
    return new QlsForm(newBaseForm, this.stylesheetNode);
  }

  setState(nextState: FormState): StatefulForm {
    const newBaseForm = this.baseForm.setState(nextState);
    return new QlsForm(newBaseForm, this.stylesheetNode);
  }

  setActivePage(nextPage: PageNode): StatefulForm {
    const nextState = this.getState().setActivePageName(nextPage.name);
    return this.setState(nextState);
  }

  getRootNode(): FormNode {
    return this.baseForm.getRootNode();
  }

  getAnswer(identifier: string): any {
    return this.baseForm.getAnswer(identifier);
  }

  accept(visitor: FieldVisitor): any {
    return this.baseForm.accept(visitor);
  }

  getActivePage(): PageNode | undefined {
    const activePageName = this.getState().getActivePageName();

    const activePage: PageNode | undefined = this.getPages().find(
        page => typeof activePageName !== 'undefined' && page.name === activePageName
    );

    // TODO: Assert that style has at least one page
    if (!activePageName) {
      return this.getPages()[0];
    }

    return activePage;
  }

  getPages(): PageNode[] {
    return this.stylesheetNode.getPages();
  }

  getField(identifier: string): FieldNode | undefined {
    return this.getFields().find(field => field.identifier === identifier);
  }

  getStyledField(identifier: string): StyledField {
    const field = this.getField(identifier);

    if (!field) {
      throw new Error(`Cannot find field ${identifier} in form.`); // TODO: Replace this with real error
    }

    return StyledField.makeFromCollections(field, this.mergedStyles, this.questionStyles);
  }

  getVariablesMap(): VariablesMap {
    return this.baseForm.getVariablesMap();
  }
}