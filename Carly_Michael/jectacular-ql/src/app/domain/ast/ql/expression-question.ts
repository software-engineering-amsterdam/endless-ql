import {QuestionBase} from '../../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {QuestionType} from '../question-type';
import {QlQuestion} from './ql-question';
import {Location} from '../location';
import {Expression} from './expressions/expression';
import {ExpressionType} from './expressions/expression-type';
import {CircularDependencyError} from '../../errors';
import * as _ from 'lodash';
import {QuestionFactory} from '../../../factories/question-factory';
import {EvaluateExpressionVisitor} from './visitors/evaluate-expression-visitor';
import {StatementVisitor} from './visitors/statement-visitor';
import {GetExpressionVariablesVisitor} from './visitors/get-expression-variables-visitor';

export class ExpressionQuestion extends QlQuestion {
  constructor(name: string, label: string, type: QuestionType<any>, readonly expression: Expression, location: Location) {
    super(name, label, type, location);
  }

  checkDependencies(): void {
    const variables = GetExpressionVariablesVisitor.evaluate(this.expression);
    const circularDependency = _.find(variables, ['identifier', this.name]);
    if (circularDependency) {
      throw new CircularDependencyError(`The expression of question ${this.name} references to itself`);
    }
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {

    const question = QuestionFactory.toFormQuestion(this.name, this.label, this.type, condition);
    question.toCalculatedQuestion((form: FormGroup) => {
      return EvaluateExpressionVisitor.evaluate(form, this.expression).getValue();
    });
    return [question];
  }

  expressionTypeValidForQuestion(expressionType: ExpressionType): boolean {
    return this.type.isCompatibleWithExpression(expressionType);
  }

  accept<T>(visitor: StatementVisitor<T>): T {
    return visitor.visitExpressionQuestion(this);
  }
}
