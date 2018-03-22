export class StyleError extends Error {
  constructor(m: string) {
    super(m);
    Object.setPrototypeOf(this, StyleError.prototype);
  }
}

export class InvalidColorError extends StyleError {
  static make(invalidColor: string) {
    const message = `Given value is not a valid color: "${invalidColor}"`;

    const error = new InvalidColorError(message);
    Object.setPrototypeOf(error, InvalidColorError.prototype);
    return error;
  }
}

export class UnknownStyleAttributeNameError extends StyleError {
  static make(invalidAttributeName: string) {
    const message = `Unkown attribute name used: "${invalidAttributeName}"`;

    const error = new UnknownStyleAttributeNameError(message);
    Object.setPrototypeOf(error, UnknownStyleAttributeNameError.prototype);
    return error;
  }
}