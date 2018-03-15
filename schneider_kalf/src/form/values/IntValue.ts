import NumberValue from "./NumberValue";
import AbstractNumberValue from "./AbstractNumberValue";

export default class IntValue extends AbstractNumberValue implements NumberValue {
  private value: number;

  constructor(value: number) {
    super();
    this.value = value;
  }

  add(other: IntValue): NumberValue {
    return new IntValue(this.getValue() + other.getValue());
  }

  minus(other: IntValue): NumberValue {
    return new IntValue(this.getValue() - other.getValue());
  }

  multiply(other: IntValue): NumberValue {
    return new IntValue(this.getValue() * other.getValue());
  }

  divide(other: IntValue): NumberValue {
    return new IntValue(Math.floor(this.getValue() / other.getValue()));
  }

  getValue(): number {
    return this.value;
  }

  getPriority(): number {
    return 1;
  }

  make(value: number): IntValue {
    return new IntValue(value);
  }

  equals(other: IntValue): boolean {
    return other.getValue() === this.getValue();
  }

  smallerThan(other: IntValue): boolean {
    return this.getValue() < other.getValue();
  }

  largerThan(other: IntValue): boolean {
    return this.getValue() > other.getValue();
  }
}