import NumberValue from "./NumberValue";
import { toDecimal } from "../evaluation/numeric_helpers";
import Decimal from "decimal.js/decimal";

export class DecimalValue implements NumberValue {
  private value: Decimal;

  constructor(value: number | Decimal) {
    this.value = toDecimal(value);
  }

  add(other: NumberValue): NumberValue {
    return new DecimalValue(this.value.add(other.getValue()));
  }

  minus(other: NumberValue): NumberValue {
    return new DecimalValue(this.value.minus(other.getValue()));
  }

  multiply(other: NumberValue): NumberValue {
    return new DecimalValue(this.value.mul(other.getValue()));
  }

  divide(other: NumberValue): NumberValue {
    return new DecimalValue(this.value.div(other.getValue()));
  }

  getValue(): Decimal {
    return this.value;
  }

  getPriority(): number {
    return 2;
  }

  make(value: number): NumberValue {
    return new DecimalValue(value);
  }
}