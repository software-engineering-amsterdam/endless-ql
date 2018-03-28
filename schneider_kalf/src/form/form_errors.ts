import { getTypeString } from "./type_checking/type_assertions";
import { FieldType } from "./FieldType";
import FieldNode from "./nodes/fields/FieldNode";
import Expression from "./nodes/expressions/Expression";
import { StyleError } from "../modules/styling/form/style_errors";

export class FormError extends Error {
  constructor(m: string) {
    super(m);
    Object.setPrototypeOf(this, FormError.prototype);
  }
}

export const makeError = <T extends FormError>(errorClass: { new(name: string): T ; }, message: string): T => {
  const error = new errorClass(message);
  Object.setPrototypeOf(error, errorClass.prototype);
  return error;
};

export class TypeCheckError extends FormError {
  expectedType: string;
  receivedType: string;

  static make(expectedType: string, receivedType: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Type check failed. Expected "${expectedType}" but received "${receivedType}".`;
    }

    const error = makeError(TypeCheckError, message);
    error.expectedType = expectedType;
    error.receivedType = receivedType;
    return error;
  }
}

export class ValuesNotComparableError extends FormError {
  left: any;
  right: any;

  static make(left: string, right: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot compare ${left} [${getTypeString(left)}] to  ${right} [${getTypeString(right)}].`;
    }

    const error = makeError(ValuesNotComparableError, message);
    error.left = left;
    error.right = right;

    return error;
  }
}

export class TypesNotComparableError extends FormError {
  left: FieldType;
  right: FieldType;

  static make(left: FieldType, right: FieldType, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot compare type ${left} to  ${right}.`;
    }

    const error = makeError(TypesNotComparableError, message)
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

    return makeError(DivisionByZeroError, message);
  }
}

// TODO: Should be removed when not called #NoDeadCode
export class NotImplementedYetError extends Error {
  static make(feature: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Feature not implemented yet: "${feature}".`;
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

    const error = makeError(UnkownFieldError, message);
    error.fieldIdentifier = identifier;
    return error;
  }
}

export class UnkownVariableIdentifierError extends FormError {
  variableIdentifier: string;

  static make(identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Unkown variable identifier: "${identifier}"`;
    }

    const error = makeError(UnkownVariableIdentifierError, message);
    error.variableIdentifier = identifier;
    return error;
  }
}

export class UnkownDefaultValueError extends FormError {
  fieldType: string;

  static make(type: FieldType, message?: string) {
    if (typeof message === 'undefined') {
      message = `No default value for type: "${type}"`;
    }

    const error = makeError(UnkownDefaultValueError, message);
    error.fieldType = type;
    return error;
  }
}

export class EmptyVariableScopeStackError extends FormError {
  identifier: string;

  static make(identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot add variable ${identifier} to empty stack.`;
    }

    const error = makeError(EmptyVariableScopeStackError, message);
    error.identifier = identifier;
    return error;
  }
}

export class FieldAlreadyDeclaredError extends FormError {
  field: FieldNode;

  static make(field: FieldNode, message?: string) {
    if (typeof message === 'undefined') {
      message = `Field "${field.identifier}" was already declared before. Please use another name.`;
    }

    const error = makeError(FieldAlreadyDeclaredError, message);
    error.field = field;
    return error;
  }
}

export class VariableNotInScopeError extends FormError {
  expression: Expression;
  identifier: string;

  static make(expression: Expression, identifier: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Unknown identifier "${identifier}" used in expression.`;
    }

    const error = makeError(VariableNotInScopeError, message);
    error.identifier = identifier;
    error.expression = expression;
    return error;
  }
}

export class ValueIsNaNError extends FormError {
  value: any;

  static make(value: any, message?: string) {
    if (typeof message === 'undefined') {
      message = `Value cannot be parsed as a number: ${value}.`;
    }
    const error = makeError(ValueIsNaNError, message);
    error.value = value;
    return error;
  }
}

export class CannotFindCommonFieldTypeError extends FormError {
  left: FieldType;
  right: FieldType;

  static make(left: FieldType, right: FieldType, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot find common field type for ${left} and ${right}.`;
    }

    const error = makeError(CannotFindCommonFieldTypeError, message);
    error.left = left;
    error.right = right;
    return error;
  }
}

export class ValueIsInvalidDateError extends FormError {
  value: string;

  static make(value: string, message?: string) {
    if (typeof message === 'undefined') {
      message = `Cannot parse date since it is invalid ${value}.`;
    }

    const error = makeError(ValueIsInvalidDateError, message);
    error.value = value;
    return error;
  }
}

export class NeedAtLeastOneFormToParseError extends FormError {
  static make(message?: string) {
    if (typeof message === 'undefined') {
      message = `The given input can not be parsed since there must be at least one form in the input.`;
    }

    return makeError(NeedAtLeastOneFormToParseError, message);
  }
}