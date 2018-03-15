import NumberValue from "./NumberValue";

export const isNumericValue = (value: any): value is NumberValue => {
  return value && typeof value === 'object' && value.type === "NumberValue";
};
