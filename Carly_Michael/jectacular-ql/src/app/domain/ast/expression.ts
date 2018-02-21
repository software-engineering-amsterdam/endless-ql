export abstract class Expression {
  constructor(public elementLeft: Expression, public elementRight: Expression) { }
}

export class IntegerExpression extends Expression {
  constructor(public value: number) {
    super(null, null);
  }
}

export class AddExpression extends Expression {
  constructor(elementLeft: Expression, elementRight: Expression) {
    super(elementLeft, elementRight);
  }
}

export class SubtractExpression extends Expression {
  constructor(elementLeft: Expression, elementRight: Expression) {
    super(elementLeft, elementRight);
  }
}

export class MultiplyExpression extends Expression {
  constructor(elementLeft: Expression, elementRight: Expression) {
    super(elementLeft, elementRight);
  }
}

export class DivideExpression extends Expression {
  constructor(elementLeft: Expression, elementRight: Expression) {
    super(elementLeft, elementRight);
  }
}
