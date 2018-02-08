export class Form {
  constructor(public name: string, public questions: Question[]) {}
}

export class Question {
  constructor(public name: string, public label: string, public type: QuestionType) { }
}

export enum QuestionType {
  INT = 1,
  DECIMAL = 2,
  MONEY = 3,
  BOOLEAN = 4,
  STRING = 5,
  DATE = 6
}
