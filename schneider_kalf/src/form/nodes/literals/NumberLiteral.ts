import ExpressionVisitor from "../visitors/ExpressionVisitor";
import Expression from "../expressions/Expression";
import { FieldType } from "../../FieldType";
import { ValueIsNaNError } from "../../form_errors";
import AbstractTreeNode from "../AbstractTreeNode";
import { Decimal } from "decimal.js";

export default class NumberLiteral extends AbstractTreeNode implements Expression {
  private type: FieldType;
  private value: Decimal;

  static fromString(text: string) {
    if (Number.isNaN(Number(text))) {
      throw ValueIsNaNError.make(text);
    }

    let type: FieldType = FieldType.Float;
    let value: number = parseFloat(text);

    if (text.indexOf('.') === -1) {
      type = FieldType.Integer;
      value = parseInt(text, 10);
    }

    return new NumberLiteral(value, type);
  }

  constructor(value: number, type?: FieldType) {
    super();

    if (!type) {
      type = FieldType.Float;
    }

    this.value = new Decimal(value);
    this.type = type;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitNumberLiteral(this);
  }

  getValue(): Decimal {
    return this.value;
  }

}