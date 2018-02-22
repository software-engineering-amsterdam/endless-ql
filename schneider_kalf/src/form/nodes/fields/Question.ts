import FieldType from "../../FieldType";
import Field from "./FieldNode";
import NodeVisitor from "../visitors/NodeVisitor";
import FormState from "../../state/FormState";

export default class Question implements Field {
  readonly label: string;
  readonly identifier: string;
  readonly type: FieldType;

  /**
   * Creates a question that lets the user input an answer.
   *
   * @param {string} identifier
   * @param {string} label
   * @param {FieldType} type
   */
  constructor(identifier: string, label: string, type: FieldType) {
    this.label = label;
    this.identifier = identifier;
    this.type = type;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitQuestion(this);
  }

  isReadOnly(): boolean {
    return false;
  }

  getAnswer(state: FormState) {
    return state.get(this.identifier);
  }
}