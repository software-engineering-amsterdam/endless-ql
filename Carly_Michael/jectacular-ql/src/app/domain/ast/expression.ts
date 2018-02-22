import {ExpressionType} from './expression-type';
import {Location} from './location';

export type BinaryOperator = '+' | '-' | '*' | '/';
export type UnaryOperator = '!' | '-';
export type LogicalOperator = '&&' | '||';
export type LiteralType = boolean | number | string | Date;

export abstract class Expression {
  constructor(public location: Location) {}
  abstract checkType(): ExpressionType;
  abstract evaluate(): LiteralType;

  protected getLocationErrorMessage(): string {
    return ` between line ${this.location.start.line}` +
      ` and col ${this.location.start.column} and line ${this.location.end.line} and col ${this.location.end.column}`;
  }
}

export class Literal extends Expression {
  constructor(public type: ExpressionType, public value: LiteralType, location: Location) {
    super(location);
  }

  checkType(): ExpressionType {
    return this.type;
  }

  evaluate(): LiteralType {
    return this.value;
  }
}
