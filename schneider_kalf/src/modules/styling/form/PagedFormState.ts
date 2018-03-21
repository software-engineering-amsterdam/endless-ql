import FormState from "../../../form/state/FormState";

export default class PagedFormState extends FormState {
  private activePageName: string | undefined;

  constructor(store?: Map<string, any>, activePage?: string) {
    super(store);
    this.activePageName = activePage;
  }

  protected instantiate(newStore?: Map<string, any>): FormState {
    return new PagedFormState(newStore, this.activePageName);
  }

  public getActivePageName(): string | undefined {
    return this.activePageName;
  }
}