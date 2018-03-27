import QuestionStyle from "./nodes/children/QuestionStyle";

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

export class UnkownQuestionUsedInLayoutError extends StyleError {
  static make(identifier: string) {
    const message = `You used an unkown question identifier in the styling,` +
        `please make sure to reference it in the QL source: "${identifier}"`;

    const error = new UnkownQuestionUsedInLayoutError(message);
    Object.setPrototypeOf(error, UnkownQuestionUsedInLayoutError.prototype);
    return error;
  }
}

export class QuestionPlacedTwiceInLayoutError extends StyleError {
  public questionStyle: QuestionStyle;
  public duplicateQuestionStyle: QuestionStyle;

  static make(questionStyle: QuestionStyle, duplicateQuestionStyle: QuestionStyle) {
    const message = `Question ${questionStyle.identifier} was placed twice.`;

    const error = new QuestionPlacedTwiceInLayoutError(message);
    error.questionStyle = questionStyle;
    error.duplicateQuestionStyle = duplicateQuestionStyle;
    Object.setPrototypeOf(error, QuestionPlacedTwiceInLayoutError.prototype);
    return error;
  }
}