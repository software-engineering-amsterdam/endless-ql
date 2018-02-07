export class Form {
  constructor(private name: string, private questions: Question[]) { }
}

export class Question {
  constructor(private name: string, private label: string, private type: Type) { }
}

export enum Type {
  INT = 1,
  DECIMAL = 2,
  MONEY = 3,
  BOOLEAN = 4,
  STRING = 5,
  DATE = 6
}
