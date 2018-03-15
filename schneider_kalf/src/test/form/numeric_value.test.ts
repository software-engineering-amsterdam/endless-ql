import IntValue from "../../form/values/IntValue";
import { DecimalValue } from "../../form/values/DecimalValue";

it('can compare IntValues', () => {
  const expression = new IntValue(5);
  expect(expression).toEqual(new IntValue(5));
  expect(expression).not.toEqual(new IntValue(6));
});

it('can compare DecimalValues', () => {
  const expression = new DecimalValue(42);
  expect(expression).toEqual(new DecimalValue(42));
  expect(expression).not.toEqual(new DecimalValue(-5));
});