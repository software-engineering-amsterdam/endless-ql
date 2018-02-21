import { getTypeString } from "./typechecking/typeAssertions";

export class FormError extends Error {
  constructor(m: string) {
    super(m);
    Object.setPrototypeOf(this, FormError.prototype);
  }
}

export class TypeCheckError extends FormError {
  expectedType: string;
  receivedType: string;

  static make(expectedType: string, receivedType: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Type check failed. Expected "${expectedType}" but received "${receivedType}`;
    }

    const error = new TypeCheckError(message);

    error.expectedType = expectedType;
    error.receivedType = receivedType;

    return error;
  }
}

export class NotComparableError extends FormError {
  left: any;
  right: any;

  static make(left: string, right: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot compare ${left} [${getTypeString(left)}] to  ${right} [${getTypeString(right)}]`;
    }

    const error = new NotComparableError(message);
    Object.setPrototypeOf(error, NotComparableError.prototype);

    error.left = left;
    error.right = right;

    return error;
  }
}

export class DivisionByZeroError extends FormError {
  static make(message?: string) {
    if (typeof message === 'undefined') {
      message = `Division by zero is not possible. `;
    }

    const error = new DivisionByZeroError(message);
    Object.setPrototypeOf(error, DivisionByZeroError.prototype);
    return error;
  }
}

export class NotImplementedYetError extends Error {
  static make(feature: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Feature not implemented yet: "${feature}"`;
    }

    return new NotImplementedYetError(message);
  }
}
