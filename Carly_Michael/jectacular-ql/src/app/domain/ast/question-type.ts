export enum QuestionType {
  INT = 1,
  BOOLEAN = 2,
  STRING = 3,
  DATE = 4
}

export class QuestionTypeUtil {
  static toString(type: QuestionType): string {
    return QuestionType[type];
  }
}
