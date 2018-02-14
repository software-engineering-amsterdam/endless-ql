import { Question } from "../fields/Question";
import { ComputedField } from "../fields/ComputedField";

interface FieldVisitor {
  visitQuestion(question: Question): any;
  visitComputedField(computedField: ComputedField): any;
}

export default FieldVisitor;