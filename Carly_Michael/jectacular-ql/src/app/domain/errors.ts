export interface Error {
  type: string;
  message: string;
}

export class UnsupportedTypeError implements Error {
  type = 'UnsupportedType';
  constructor(public message: string) { }
}

export class UnknownQuestionError implements Error {
  type = 'UnknownQuestion';
  constructor(public message: string) {}
}

export class TypeError implements Error {
  type = 'TypeError';
  constructor(public message: string) {}
}

export class DuplicateIdentifierError implements Error {
  type = 'DuplicateIdentifier';
  constructor(public message: string) {}
}

export class UnknownOperatorError implements Error {
  type = 'UnknownOperator';
  constructor(public message: string) {}
}
