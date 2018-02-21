import ValueWrapper from "./ValueWrapper";

export default class BooleanWrapper implements ValueWrapper {
  private value: boolean;

  constructor(value: boolean) {
    this.value = value || false;
  }

  set(value: boolean): BooleanWrapper {
    return new BooleanWrapper(value);
  }

  get(): boolean {
    return this.value;
  }

  toString() {
    return (this.value) ? "true" : "false";
  }
}