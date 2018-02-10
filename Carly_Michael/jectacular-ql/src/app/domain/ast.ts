export class Form {
  constructor(public name: string, public statements: Statement[]) {}
}

export class Statement {
  constructor() {}
}

export class Question extends Statement {
  constructor(public name: string, public label: string, public type: QuestionType) {
    super();
  }
}

export class If extends Statement {
  constructor(public condition: string, public statements: Statement[]) {
    super();
  }
}


export enum QuestionType {
  INT = 1,
  DECIMAL = 2,
  MONEY = 3,
  BOOLEAN = 4,
  STRING = 5,
  DATE = 6
}
