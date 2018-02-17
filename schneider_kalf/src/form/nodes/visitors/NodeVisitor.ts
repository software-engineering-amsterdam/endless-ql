import ExpressionVisitor from "./ExpressionVisitor";
import IfCondition from "../conditions/IfCondition";
import FormNode from "../FormNode";
import Question from "../fields/Question";
import ComputedField from "../fields/ComputedField";

/**
 * Visitor that visits every child of a form node.
 */
interface NodeVisitor extends ExpressionVisitor {
  visitQuestion(question: Question): any;
  visitComputedField(computedField: ComputedField): any;
  visitIfCondition(ifCondition: IfCondition): any;
  visitForm(form: FormNode): any;
}

export default NodeVisitor;