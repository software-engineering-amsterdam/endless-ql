import Condition from "./Condition";
import NodeVisitor from "../visitors/NodeVisitor";

export default class IfCondition extends Condition {
  accept(visitor: NodeVisitor): any {
    return visitor.visitIfCondition(this);
  }
}