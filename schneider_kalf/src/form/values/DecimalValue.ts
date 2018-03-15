import NumberValue from "./NumberValue";
import { toDecimal } from "../evaluation/numeric_helpers";
import Decimal from "decimal.js/decimal";
import AbstractNumberValue from "./AbstractNumberValue";

export class DecimalValue extends AbstractNumberValue implements NumberValue {
  private value: Decimal;

  constructor(value: number | Decimal) {
    super();
    this.value = toDecimal(value);
  }

  add(other: DecimalValue): NumberValue {
    return new DecimalValue(this.getValue().add(other.getValue()));
  }

  minus(other: DecimalValue): NumberValue {
    return new DecimalValue(this.getValue().minus(other.getValue()));
  }

  multiply(other: DecimalValue): NumberValue {
    return new DecimalValue(this.getValue().mul(other.getValue()));
  }

  divide(other: DecimalValue): NumberValue {
    return new DecimalValue(this.getValue().div(other.getValue()));
  }

  getValue(): Decimal {
    return this.value;
  }

  getPriority(): number {
    return 2;
  }

  make(value: number): DecimalValue {
    return new DecimalValue(value);
  }

  equals(other: NumberValue): boolean {
    return other.getValue().equals(this.getValue());
  }

  smallerThan(other: NumberValue): boolean {
    return this.getValue().lessThan(other.getValue());
  }

  largerThan(other: NumberValue): boolean {
    return this.getValue().greaterThan(other.getValue());
  }

  toString(): string {
    return this.getValue().toString();
  }
}