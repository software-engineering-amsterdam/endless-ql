import FormState from "../../../form/state/FormState";

export default class StyledFormState extends FormState {
  private activePage: string | undefined;

  constructor(store?: Map<string, any>, activePage?: string) {
    super(store);
    this.activePage = activePage;
  }
}