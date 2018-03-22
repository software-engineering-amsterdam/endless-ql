import BaseAttribute from "../BaseAttribute";

export default class FontAttribute extends BaseAttribute {
  private value: string;

  constructor(value: string) {
    super();
    this.value = value;
  }

  getCssValues(): object {
    return {[this.getName()]: this.getStringValue() };
  }

  getName(): string {
    return "font";
  }

  getStringValue(): string {
    return this.value;
  }
}