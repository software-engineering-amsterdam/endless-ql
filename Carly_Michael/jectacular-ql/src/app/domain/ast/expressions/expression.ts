import {ExpressionType} from './expression-type';
import {Location} from '../location';
import {Question} from '../question';
import {FormGroup} from '@angular/forms';
import {Variable} from './variable';
import {QuestionType} from '../question-type';

export type ArithmeticOperator = '+' | '-' | '*' | '/';
export type ComparisonOperator = '>' | '<' | '>=' | '<=';
export type UnaryOperator = '!' | '-';
export type LogicalOperator = '&&' | '||';
export type EqualityOperator = '==' | '!=';
export type BinaryOperator = ArithmeticOperator | ComparisonOperator | LogicalOperator | EqualityOperator;
export type LiteralType = boolean | number | string | Date;

export abstract class Expression {
  constructor(public location: Location) {
  }

  abstract checkType(allQuestions: Question[]): ExpressionType;

  abstract evaluate(form: FormGroup): LiteralType;

  abstract getVariables(): Variable[];

  protected getLocationErrorMessage(): string {
    return ` between line ${this.location.start.line}` +
      ` and col ${this.location.start.column} and line ${this.location.end.line} and col ${this.location.end.column}`;
  }

  protected toExpressionType(questionType: QuestionType): ExpressionType {
    switch (questionType) {
      case QuestionType.DECIMAL || QuestionType.INT || QuestionType.MONEY:
        return ExpressionType.NUMBER;
      case QuestionType.STRING:
        return ExpressionType.STRING;
      case QuestionType.BOOLEAN:
        return ExpressionType.BOOLEAN;
      case QuestionType.DATE:
        return ExpressionType.DATE;
    }
  }
}

export class Literal extends Expression {
  constructor(public type: ExpressionType, public value: LiteralType, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return [];
  }

  checkType(): ExpressionType {
    return this.type;
  }

  evaluate(form: FormGroup): LiteralType {
    return this.value;
  }
}