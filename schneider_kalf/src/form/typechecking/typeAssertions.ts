import { DivisionByZeroError, TypeCheckError } from "../errors";

export const getTypeString = (value: any) => {
  if (Array.isArray(value)) {
    return "array";
  }

  if (value === null) {
    return "null";
  }

  if (typeof value === "object" && value.constructor) {
    return value.constructor.name;
  }

  return typeof value;
};

export const assertType = (value: any, expectedType: string) => {
  if (typeof value !== expectedType) {
    throw TypeCheckError.make(expectedType, getTypeString(value));
  }

  return value;
};

export const assertBoolean = (value: any) => {
  return assertType(value, "boolean");
};

export const assertString = (value: any) => {
  return assertType(value, "string");
};

export const assertNumeric = (value: any) => {
  return assertType(value, "number");
};

export const assertComparable = (value: any) => {
  if (["string", "number", "boolean"].indexOf(typeof  value) === -1) {
    throw TypeCheckError.make("compareable", getTypeString(value));
  }

  return value;
};

export const assertValidDivision = (dividend: any, divisor: any) => {
  dividend = assertNumeric(dividend);
  divisor = assertNumeric(divisor);

  if (dividend !== 0 && divisor === 0) {
    throw DivisionByZeroError.make();
  }

  return {dividend, divisor};
};

export const assertSameType = (left: any, right: any) => {
  return typeof left === typeof right;
};