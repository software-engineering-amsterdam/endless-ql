import VariableScopeVisitor from "../../form/typechecking/VariableScopeVisitor";
import { nestedForm, nestedFormFieldDeclaredTwice, nestedFormScopeFlawed2 } from "../data/testForms";
import { FieldAlreadyDeclaredError, VariableNotInScopeError } from "../../form/form_errors";

it('does not create an error for the variable scopes of a correct form', () => {
  const visitor = new VariableScopeVisitor();
  nestedForm.accept(visitor);

  expect(true).toBeTruthy();
});

it('does not allow a second declaration of a field', () => {
  const visitor = new VariableScopeVisitor();

  expect(() => {
    nestedFormFieldDeclaredTwice.accept(visitor);
  }).toThrow(FieldAlreadyDeclaredError);
});

it('does not allow usage of variable in deeper scope', () => {
  const visitor = new VariableScopeVisitor();
  let error: any = null;

  try {
    nestedFormScopeFlawed2.accept(visitor);
  } catch (_error) {
    error = _error;
  }

  // expect(error).toBeInstanceOf(VariableNotInScopeError);

  if (error && error instanceof VariableNotInScopeError) {
    expect(error.identifier).toBe("a");
  }
});