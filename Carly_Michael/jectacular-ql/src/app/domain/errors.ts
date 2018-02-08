export interface Error {
  type: string;
  message: string;
}

export class UnsupportedTypeError implements Error {
  type = 'UnsupportedType';
  constructor(public message: string) { }
}
