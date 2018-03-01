import { getTypeString } from "./typechecking/typeAssertions";
import FieldType from "./FieldType";
import FieldNode from "./nodes/fields/FieldNode";
import Expression from "./nodes/expressions/Expression";

export class FormError extends Error {
  constructor(m: string) {
    super(m);
    Object.setPrototypeOf(this, FormError.prototype);
  }
}

export class TypeCheckError extends FormError {
  expectedType: string;
  receivedType: string;

  static make(expectedType: string, receivedType: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Type check failed. Expected "${expectedType}" but received "${receivedType}`;
    }

    const error = new TypeCheckError(message);

    error.expectedType = expectedType;
    error.receivedType = receivedType;

    return error;
  }
}

export class NotComparableError extends FormError {
  left: any;
  right: any;

  static make(left: string, right: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot compare ${left} [${getTypeString(left)}] to  ${right} [${getTypeString(right)}]`;
    }

    const error = new NotComparableError(message);
    Object.setPrototypeOf(error, NotComparableError.prototype);

    error.left = left;
    error.right = right;

    return error;
  }
}

export class DivisionByZeroError extends FormError {
  static make(message?: string) {
    if (typeof message === 'undefined') {
      message = `Division by zero is not possible. `;
    }

    const error = new DivisionByZeroError(message);
    Object.setPrototypeOf(error, DivisionByZeroError.prototype);
    return error;
  }
}

export class NotImplementedYetError extends Error {
  static make(feature: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Feature not implemented yet: "${feature}"`;
    }

    return new NotImplementedYetError(message);
  }
}

export class UnkownFieldError extends FormError {
  fieldIdentifier: string;

  static make(identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Unkown field ${identifier}.`;
    }

    const error = new UnkownFieldError(message);
    error.fieldIdentifier = identifier;
    Object.setPrototypeOf(error, UnkownFieldError.prototype);
    return error;
  }
}

export class UnkownVariableIdentifierError extends FormError {
  variableIdentifier: string;

  static make(identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Unkown variable identifier: "${identifier}"`;
    }

    const error = new UnkownVariableIdentifierError(message);
    error.variableIdentifier = identifier;
    Object.setPrototypeOf(error, UnkownVariableIdentifierError.prototype);
    return error;
  }
}

export class UnkownDefaultValueError extends FormError {
  fieldType: string;

  static make(type: FieldType, message?: string) {
    if (typeof message === 'undefined') {
      message = `No default value for type: "${type}"`;
    }

    const error = new UnkownDefaultValueError(message);
    error.fieldType = type;
    Object.setPrototypeOf(error, UnkownDefaultValueError.prototype);
    return error;
  }
}

export class EmptyVariableScopeStackError extends FormError {
  identifier: string;

  static make(identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot add variable ${identifier} to empty stack.`;
    }

    const error = new EmptyVariableScopeStackError(message);
    error.identifier = identifier;
    Object.setPrototypeOf(error, EmptyVariableScopeStackError.prototype);
    return error;
  }
}

export class FieldAlreadyDeclaredError extends FormError {
  field: FieldNode;

  static make(field: FieldNode, message?: string) {
    if (typeof message === 'undefined') {
      message = `Field "${field.identifier}" was already declared before. Please use another name.`;
    }

    const error = new FieldAlreadyDeclaredError(message);
    error.field = field;
    Object.setPrototypeOf(error, FieldAlreadyDeclaredError.prototype);
    return error;
  }
}

export class VariableNotInScopeError extends FormError {
  expression: Expression;
  identifier: string;

  static make(expression: Expression, identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Uknown identifier "${identifier}" used in expression.`;
    }

    const error = new VariableNotInScopeError(message);
    error.identifier = identifier;
    error.expression = expression;
    Object.setPrototypeOf(error, VariableNotInScopeError.prototype);
    return error;
  }
}