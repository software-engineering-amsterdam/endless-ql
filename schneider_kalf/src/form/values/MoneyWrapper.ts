import ValueWrapper from "./ValueWrapper";
import { Money } from 'ts-money';
import constants from "../../config/constants";

export default class MoneyWrapper implements ValueWrapper {
  private value: Money;

  constructor(value: Money) {
    this.value = value || new Money(0, constants.DEFAULT_CURRENCY);
  }

  set(value: Money): MoneyWrapper {
    return new MoneyWrapper(value);
  }

  get(): Money {
    return this.value;
  }

  toString() {
    return this.value.toString();
  }
}