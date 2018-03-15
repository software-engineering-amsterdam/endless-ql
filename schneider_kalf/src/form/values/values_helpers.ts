import NumberValue from "./NumberValue";
import { FieldType } from "../FieldType";
import IntValue from "./IntValue";
import { DecimalValue } from "./DecimalValue";

export const isNumericValue = (value: any): value is NumberValue => {
  return value && typeof value === 'object' && value.type === "NumberValue";
};

export const makeNumberValue = (value: string, type?: FieldType): NumberValue => {
  if (type === FieldType.Integer) {
    return new IntValue(parseInt(value, 10));
  }

  return new DecimalValue(parseFloat(value));
};