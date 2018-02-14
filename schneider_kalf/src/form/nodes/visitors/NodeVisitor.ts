import ExpressionVisitor from "./ExpressionVisitor";
import IfCondition from "../conditions/IfCondition";
import FormNode from "../FormNode";
import Question from "../fields/Question";
import ComputedField from "../fields/ComputedField";

interface NodeVisitor extends ExpressionVisitor {
  visitQuestion(question: Question): any;
  visitComputedField(computedField: ComputedField): any;
  visitIfCondition(ifCondition: IfCondition): any;
  visitForm(form: FormNode): any;
}

export default NodeVisitor;