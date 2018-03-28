import AbstractStyleNode from "../AbstractStyleNode";
import SectionChild from "./SectionChild";
import StyleAttribute from "../StyleAttribute";
import StyleNodeVisitor from "../../visitors/StyleNodeVisitor";
import SectionNode from "../containers/SectionNode";
import FieldNode from "../../../../../form/nodes/fields/FieldNode";

export default class QuestionStyle extends AbstractStyleNode implements SectionChild {
  readonly identifier: string;
  readonly children: StyleAttribute[];

  constructor(identifier: string, children: StyleAttribute[]) {
    super();
    this.identifier = identifier;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor) {
    return visitor.visitQuestionStyle(this);
  }

  isSection(): this is SectionNode {
    return false;
  }

  isRendered(): boolean {
    return true;
  }

  isQuestionStyle(): this is QuestionStyle {
    return true;
  }

  appliesToField(field: FieldNode) {
    return field.identifier === this.identifier;
  }
}