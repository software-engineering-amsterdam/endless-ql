import FieldNode from "./FieldNode";
import { FieldType } from "../../FieldType";
import NodeVisitor from "../visitors/NodeVisitor";
import FormState from "../../state/FormState";
import NodeLocation from "../location/NodeLocation";

/**
 * Decorator for Fields that makes the Field "decoratable" for future usage.
 */
export default class FieldNodeDecorator implements FieldNode {
  private fieldToBeDecorated: FieldNode;

  get identifier(): string {
    return this.fieldToBeDecorated.identifier;
  }

  get label(): string {
    return this.fieldToBeDecorated.label;
  }

  get type(): FieldType {
    return this.fieldToBeDecorated.type;
  }

  constructor(fieldToBeDecorated: FieldNode) {
    this.fieldToBeDecorated = fieldToBeDecorated;
  }

  accept(visitor: NodeVisitor) {
    return this.fieldToBeDecorated.accept(visitor);
  }

  isReadOnly(): boolean {
    return this.fieldToBeDecorated.isReadOnly();
  }

  computeAnswer(state: FormState) {
    return this.fieldToBeDecorated.computeAnswer(state);
  }

  setLocation(location: NodeLocation): void {
    this.fieldToBeDecorated.setLocation(location);
  }

  getLocation(): NodeLocation {
    return this.fieldToBeDecorated.getLocation();
  }
}