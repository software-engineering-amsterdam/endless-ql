export enum QuestionType {
  INT = 1,
  DECIMAL = 2,
  BOOLEAN = 3,
  STRING = 4,
  DATE = 5
}

export class QuestionTypeUtil {
  static toString(type: QuestionType): string {
    return QuestionType[type];
  }
}
