import {Location} from '../../location';
import {ExpressionVisitor} from '../visitors/expression-visitor';

export type LiteralType = boolean | number | string | Date;

export abstract class Expression {
  constructor(public location: Location) {
  }

  abstract accept<T>(visitor: ExpressionVisitor<T>): T;
}
