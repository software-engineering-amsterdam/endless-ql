import {Statement} from './statement';
import {QuestionBase} from '../../angular-questions/question-base';
import {FormGroup} from '@angular/forms';
import {Question} from './question';
import * as _ from 'lodash';
import {TypeError} from '../../errors';
import {Location} from '../location';
import {Expression, LiteralType} from './expressions/expression';
import {ExpressionType, ExpressionTypeUtil} from './expressions/expression-type';
import {Variable} from './expressions/variable';

export class If extends Statement {
  constructor(public condition: Expression, public statements: Statement[], public elseStatements: Statement[], location: Location) {
    super(location);
  }

  getVariables(): Variable[] {
    let allVariables = [];
    const allStatements = this.statements.concat(this.elseStatements);
    for (const statement of allStatements) {
      allVariables.push(statement.getVariables());
    }
    allVariables.push(this.condition.getVariables());
    allVariables = _.flatten(allVariables);
    return allVariables;
  }

  checkType(allQuestions: Question[]): void {
    const expressionType = this.condition.checkType(allQuestions);

    // throw errors if it is not available or if the type is wrong
    if (expressionType !== ExpressionType.BOOLEAN) {
      throw new TypeError(`Expected type boolean for ${ExpressionTypeUtil.toString(expressionType)} for usage in if statement `
        + this.getLocationErrorMessage());
    }
  }

  getQuestions(): Question[] {
    const subQuestions = [];

    // get questions of statements in body of the if
    for (const statement of this.statements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    for (const statement of this.elseStatements) {
      const questions = statement.getQuestions();

      if (questions.length > 0) {
        subQuestions.push(statement.getQuestions());
      }
    }

    return _.flattenDeep(subQuestions);
  }

  toFormQuestion(formQuestions: ReadonlyArray<QuestionBase<any>>,
                 condition?: (form: FormGroup) => boolean): ReadonlyArray<QuestionBase<any>> {

    // generate function that should be evaluated for the condition
    const conditionFunction = ((form: FormGroup) => {
      if (condition) {
        return condition(form) && this.condition.evaluate(form);
      }
      return this.condition.evaluate(form);
    });

    const elseConditionFunction = ((form: FormGroup) => {
      return !conditionFunction(form);
    });

    return this.generateQuestionsForBody(formQuestions, conditionFunction, elseConditionFunction);
  }

  private generateQuestionsForBody(formQuestions: ReadonlyArray<QuestionBase<any>>,
                                   conditionFunction: (form: FormGroup) => LiteralType,
                                   elseConditionFunction: (form: FormGroup) => LiteralType): ReadonlyArray<QuestionBase<any>> {
    let formQuestionsToReturn: QuestionBase<any>[] = [];
    for (const statement of this.statements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, conditionFunction));
    }

    for (const statement of this.elseStatements) {
      formQuestionsToReturn = formQuestionsToReturn.concat(statement.toFormQuestion(formQuestions, elseConditionFunction));
    }

    return formQuestionsToReturn;
  }
}
