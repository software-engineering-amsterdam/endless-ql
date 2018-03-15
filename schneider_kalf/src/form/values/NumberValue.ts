export default interface NumberValue {
  add(other: NumberValue): NumberValue;

  minus(other: NumberValue): NumberValue;

  multiply(other: NumberValue): NumberValue;

  divide(other: NumberValue): NumberValue;

  getValue(): any;

  getPriority(): number;

  make(value: number): NumberValue;
}