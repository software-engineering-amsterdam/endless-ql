import FieldNode from "./FieldNode";
import { FieldType } from "../../FieldType";
import NodeVisitor from "../visitors/NodeVisitor";
import FormState from "../../state/FormState";
import NodeLocation from "../location/NodeLocation";

/**
 * Decorator for Fields that makes the Field "decoratable" for future usage.
 */
export default class FieldNodeDecorator implements FieldNode {
  private _fieldToBeDecorated: FieldNode;

  get identifier(): string {
    return this._fieldToBeDecorated.identifier;
  }

  get label(): string {
    return this._fieldToBeDecorated.label;
  }

  get type(): FieldType {
    return this._fieldToBeDecorated.type;
  }

  constructor(fieldToBeDecorated: FieldNode) {
    this._fieldToBeDecorated = fieldToBeDecorated;
  }

  accept(visitor: NodeVisitor) {
    return visitor.visitFieldDecorator(this);
  }

  isReadOnly(): boolean {
    return this._fieldToBeDecorated.isReadOnly();
  }

  computeAnswer(state: FormState) {
    return this._fieldToBeDecorated.computeAnswer(state);
  }

  setLocation(location: NodeLocation): void {
    this._fieldToBeDecorated.setLocation(location);
  }

  getLocation(): NodeLocation {
    return this._fieldToBeDecorated.getLocation();
  }

  getBaseField(): FieldNode {
    return this._fieldToBeDecorated;
  }
}