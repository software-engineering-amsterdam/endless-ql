import IfCondition from "../../form/nodes/conditions/IfCondition";
import BooleanLiteral from "../../form/nodes/expressions/literals/BooleanLiteral";
import { typeCheck } from "../../form/type_checking/type_check_functions";
import { FieldType } from "../../form/FieldType";
import NumberLiteral from "../../form/nodes/expressions/literals/NumberLiteral";
import { TypeCheckError } from "../../form/form_errors";
import Equals from "../../form/nodes/expressions/comparisons/Equals";
import Addition from "../../form/nodes/expressions/arithmetic/Addition";
import Division from "../../form/nodes/expressions/arithmetic/Division";
import Subtraction from "../../form/nodes/expressions/arithmetic/Subtraction";
import VariableIdentifier from "../../form/nodes/expressions/VariableIdentifier";
import { VariableInformation, variablesToMap } from "../../form/VariableIntformation";

it("allows booleans in if conditions", () => {
  const expression = new IfCondition(new BooleanLiteral(true), []);
  expect(typeCheck(expression)).toBe(FieldType.Boolean);
});

it("does not allow numbers in if conditions", () => {
  const expression = new IfCondition(new NumberLiteral(32), []);

  expect(() => {
    typeCheck(expression);
  }).toThrow(TypeCheckError);
});

it("allows integer comparisons", () => {
  const addition = new Addition(
      new NumberLiteral(2),
      new NumberLiteral(31)
  );

  const expression = new Equals(
      new NumberLiteral(32),
      addition
  );

  expect(typeCheck(addition)).toBe(FieldType.Integer);
  expect(typeCheck(expression)).toBe(FieldType.Boolean);
});

it("uses the lowest possible numeric type", () => {
  const integerDivision = new Division(
      new NumberLiteral(10),
      new NumberLiteral(3)
  );

  const floatAddition = new Addition(
      new NumberLiteral(2),
      new NumberLiteral(2.1)
  );

  const moneyVariables: VariableInformation[] = [
    {
      identifier: "someMoney",
      type: FieldType.Money
    }
  ];

  const moneySubtraction = new Subtraction(
      new VariableIdentifier("someMoney"),
      new NumberLiteral(5.2)
  );

  expect(typeCheck(integerDivision)).toBe(FieldType.Integer);
  expect(typeCheck(floatAddition)).toBe(FieldType.Float);
  expect(typeCheck(moneySubtraction, variablesToMap(moneyVariables))).toBe(FieldType.Money);
});

// TODO: Add test cases for more complex formulas