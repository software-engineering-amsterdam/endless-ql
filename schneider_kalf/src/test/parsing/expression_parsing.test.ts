import { getQlParser } from "../../parsing/parsing_helpers";
import FormNode from "../../form/nodes/FormNode";
import Addition from "../../form/nodes/expressions/arithmetic/Addition";
import ComputedField from "../../form/nodes/fields/ComputedField";
import NumberLiteral from "../../form/nodes/expressions/arithmetic/NumberLiteral";

const qlParser = getQlParser();

it("can parse number literals", () => {
  const input = `form taxOfficeExample {
                  "Did you sell a house in 2010?"
                   hasSoldHouse: integer = 
                    (50 + 10)
                   }`;
  const forms: FormNode[] = qlParser.parse(input);

  expect(forms.length).toBe(1);
  expect(forms[0]).toBeInstanceOf(FormNode);
  expect(forms[0].statements.length).toBe(1);
  expect(forms[0].statements[0]).toBeInstanceOf(ComputedField);

  const field: any = forms[0].statements[0];
  expect(field.formula).toBeInstanceOf(Addition);

  const left: NumberLiteral = field.formula.left;
  const right: NumberLiteral = field.formula.right;
  expect(left).toBeInstanceOf(NumberLiteral);
  expect(right).toBeInstanceOf(NumberLiteral);
  expect(left.getValue()).toBe(50);
  expect(right.getValue()).toBe(10);
});