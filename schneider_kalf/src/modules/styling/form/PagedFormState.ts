import FormState from "../../../form/state/FormState";

export default class PagedFormState extends FormState {
  private activePage: string | undefined;

  constructor(store?: Map<string, any>, activePage?: string) {
    super(store);
    this.activePage = activePage;
  }

  protected instantiate(newStore?: Map<string, any>): FormState {
    return new PagedFormState(newStore, this.activePage);
  }
}