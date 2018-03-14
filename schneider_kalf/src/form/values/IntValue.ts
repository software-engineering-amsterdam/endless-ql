import NumberValue from "./NumberValue";

export default class IntValue implements NumberValue {
  private value: number;

  constructor(value: number) {
    this.value = value;
  }

  add(other: NumberValue): NumberValue {
    if (other.getPriority() > this.getPriority()) {
      return other.add(this);
    }

    return this.getValue() + other.getValue();
  }

  minus(other: NumberValue): NumberValue {
    if (other.getPriority() > this.getPriority()) {
      return other.make(this.getValue()).minus(other);
    }

    return new IntValue(Math.floor(this.getValue() - other.getValue()));
  }

  multiply(other: NumberValue): NumberValue {
    if (other.getPriority() > this.getPriority()) {
      return other.multiply(this);
    }

    return new IntValue(this.getValue() * other.getValue());
  }

  divide(other: NumberValue): NumberValue {
    if (other.getPriority() > this.getPriority()) {
      return other.make(this.getValue()).divide(other);
    }

    return new IntValue(Math.floor(this.getValue() / other.getValue()));
  }

  getValue(): number {
    return this.value;
  }

  getPriority(): number {
    return 1;
  }

  make(value: number): NumberValue {
    return new IntValue(value);
  }
}