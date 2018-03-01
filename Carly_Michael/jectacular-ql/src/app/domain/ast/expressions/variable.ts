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
    console.log('referenced question', this.referencedQuestion);
    return this.toExpressionType(this.referencedQuestion.type);
  }

  evaluate(form: FormGroup): LiteralType {
    const referencedQuestion = form.controls[this.identifier];
    if (referencedQuestion) {
      return referencedQuestion.value;
    } else {
      throw new UnknownQuestionError(`Question for identifier ${this.identifier} could not be found`
        + this.getLocationErrorMessage());
    }
  }
}
