export interface StyleValue {
  getValueAsString(): string;
}

export class RgbValue implements StyleValue {
  constructor(public red: number, public green: number, public blue: number) { }
  getValueAsString(): string {
    return '#' + this.red.toString() + this.green.toString() + this.blue.toString();
  }
}

export class NumberValue implements StyleValue {
  constructor(public value: number) { }

  getValueAsString(): string {
    return this.value.toString() + 'px';
  }
}

export class StringValue implements StyleValue {
  constructor(public value: string) { }

  getValueAsString(): string {
    return this.value;
  }
}
