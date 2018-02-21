import ValueWrapper from "./ValueWrapper";

export default class StringWrapper implements ValueWrapper {
  private value: string;

  constructor(value: string) {
    this.value = value || "";
  }

  set(value: string): StringWrapper {
    return new StringWrapper(value);
  }

  get(): string {
    return this.value;
  }

  toString() {
    return this.value;
  }
}