import { TypeCheckError } from "../errors";

export const assertBoolean = (value: any) => {
  return assertType(value, "boolean");
};

export const assertString = (value: any) => {
  return assertType(value, "string");
};

export const assertNumeric = (value: any) => {
  return assertType(value, "number");
};

export const assertType = (value: any, expectedType: string) => {
  if (typeof value !== expectedType) {
    throw TypeCheckError.make(expectedType, typeof value);
  }

  return value;
};