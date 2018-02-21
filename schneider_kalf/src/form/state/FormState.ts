export default class FormState {
  private store: Map<string, any>;

  constructor(store: Map<string, any>) {
    this.store = store;
  }
}