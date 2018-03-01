import {Expression, LiteralType} from './expression';
import {Question} from '../question';
import {ExpressionType} from './expression-type';
import {Location} from '../location';
import {FormGroup} from '@angular/forms';
import {UnknownQuestionError} from '../../errors';

export class Variable extends Expression {
  public referencedQuestion: Question;

  constructor(public identifier: string, location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    return [this];
  }

  checkType(allQuestions: Question[]): ExpressionType {
    return this.toExpressionType(this.referencedQuestion.type);
  }

  evaluate(form: FormGroup): LiteralType {
    const referencedControl = form.controls[this.identifier];
    if (referencedControl) {
      console.log('in if', referencedControl.value);
      /* Angular sets the value for a form control with undefined as value to an object {value: ""}
         If there is a value, instead of the object there will be a value, which means value.value is undefined */
      return referencedControl.value.value === undefined ? referencedControl.value : undefined;
    } else {
      throw new UnknownQuestionError(`Question for identifier ${this.identifier} could not be found`
        + this.getLocationErrorMessage());
    }
  }
}
