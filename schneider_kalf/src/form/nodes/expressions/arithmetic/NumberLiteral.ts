import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";
import { FieldType } from "../../../FieldType";

export default class NumberLiteral implements Expression {
  private type: FieldType;
  private value: number;

  static fromString(text: string) {
    let type: FieldType = FieldType.Float;
    let value: number = parseFloat(text);

    if (text.indexOf('.') === -1) {
      type = FieldType.Integer;
      value = parseInt(text, 10);
    }

    return new NumberLiteral(value, type);
  }

  constructor(value: number, type?: FieldType) {
    if (!type) {
      type = FieldType.Float;
    }

    this.value = value;
    this.type = type;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitNumberLiteral(this);
  }

  getValue() {
    return this.value;
  }

}