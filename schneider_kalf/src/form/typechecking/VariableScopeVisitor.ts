import FieldVisitor from "../nodes/visitors/FieldVisitor";
import ComputedField from "../nodes/fields/ComputedField";
import Question from "../nodes/fields/Question";
import IfCondition from "../nodes/conditions/IfCondition";
import FormNode from "../nodes/FormNode";
import { VariableScopeStack } from "./VariableScopeStack";
import FieldNode from "../nodes/fields/FieldNode";
import { FieldAlreadyDeclaredError, VariableNotInScopeError } from "../form_errors";
import { getUsedVariables } from "../form_helpers";
import Expression from "../nodes/expressions/Expression";

export default class VariableScopeVisitor implements FieldVisitor {
  private _stack: VariableScopeStack;

  constructor() {
    this._stack = new VariableScopeStack();
  }

  visitQuestion(question: Question): any {
    this.addToStack(question);
  }

  visitComputedField(computedField: ComputedField): any {
    this.containsAllVariablesOrFail(computedField.formula);

    this.addToStack(computedField);
  }

  visitIfCondition(ifCondition: IfCondition): any {
    this.containsAllVariablesOrFail(ifCondition.predicate);

    this._stack.moveDown();
    ifCondition.then.forEach(statement => statement.accept(this));
    this._stack.moveUp();
  }

  visitForm(form: FormNode): any {
    this._stack.moveDown();
    form.statements.forEach(statement => statement.accept(this));
    this._stack.moveUp();
  }

  private containsAllVariablesOrFail(expression: Expression) {
    const variables = getUsedVariables(expression);

    variables.forEach(identifier => {
      if (!this._stack.contains(identifier)) {
        throw VariableNotInScopeError.make(expression, identifier);
      }
    });
  }

  private addToStack(field: FieldNode) {
    if (this._stack.wasAlreadyDeclared(field.identifier)) {
      throw (FieldAlreadyDeclaredError.make(field));
    }

    this._stack.add(field.identifier);
  }
}
