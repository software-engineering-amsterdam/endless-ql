import ExpressionVisitor from "./ExpressionVisitor";
import { Question } from "../fields/Question";
import { ComputedField } from "../fields/ComputedField";

interface NodeVisitor extends ExpressionVisitor, FieldVisitor {
  visitQuestion(question: Question): any;
  visitComputedField(computedField: ComputedField): any;
}

export default NodeVisitor;