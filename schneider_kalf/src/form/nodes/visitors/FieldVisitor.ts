import QuestionNode from "../fields/QuestionNode";
import ComputedField from "../fields/ComputedField";
import IfCondition from "../conditions/IfCondition";
import FormNode from "../FormNode";
import FieldNodeDecorator from "../fields/FieldNodeDecorator";

interface FieldVisitor {
  visitFieldDecorator(fieldDecorator: FieldNodeDecorator): any;

  visitQuestion(question: Question): any;

  visitComputedField(computedField: ComputedField): any;

  visitIfCondition(ifCondition: IfCondition): any;

  visitForm(form: FormNode): any;
}

export default FieldVisitor;