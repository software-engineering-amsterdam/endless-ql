import AbstractStyleNode from "../AbstractStyleNode";
import StyleAttribute from "../StyleAttribute";
import StyleNodeVisitor from "../../visitors/StyleNodeVisitor";
import { NotImplementedYetError } from "../../../../../form/form_errors";

export default class WidgetAttribute extends AbstractStyleNode implements StyleAttribute {
  readonly name: string;
  readonly value: string;
  readonly options: string[] | undefined;

  constructor(name: string, value: string, options?: string[]) {
    super();
    this.name = name;
    this.value = value;
    this.options = options;
  }

  accept(visitor: StyleNodeVisitor) {
    return visitor.visitWidgetAttribute(this);
  }

  getName(): string {
    return this.name;
  }

  getStringValue(): string {
    // TODO: Keep it like this?
    throw new NotImplementedYetError("Cannot get string value from widget attribute.");
  }
}