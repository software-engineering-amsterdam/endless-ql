import {Variable} from './expressions/variable';
import {emptyLoc} from '../location';
import {QlQuestion} from './ql-question';
import {IntQuestionType} from '../question-type';
import {If} from './if';
import {Literal} from './expressions/expression';
import {ExpressionType} from './expressions/expression-type';

describe('if statement', () => {
  it('Should check for impossible if statements', () => {
    const expression = new Variable('questionInBody', emptyLoc);
    const question = new QlQuestion('questionInBody', '', new IntQuestionType(), emptyLoc);
    const ifStatement = new If(expression, [question], [], emptyLoc);

    expect(() => ifStatement.checkType([question])).toThrow();
  });

  it('Should check if expression type is boolean', () => {
    const boolExpression = new Literal(ExpressionType.BOOLEAN, true, emptyLoc);
    const dateExpression = new Literal(ExpressionType.DATE, true, emptyLoc);
    const numberExpression = new Literal(ExpressionType.NUMBER, true, emptyLoc);
    const stringExpression = new Literal(ExpressionType.STRING, true, emptyLoc);
    const ifStatement = new If(null, [], [], emptyLoc);

    ifStatement.condition = boolExpression;
    expect(() => ifStatement.checkType([])).not.toThrow();
    ifStatement.condition = dateExpression;
    expect(() => ifStatement.checkType([])).toThrow();
    ifStatement.condition = numberExpression;
    expect(() => ifStatement.checkType([])).toThrow();
    ifStatement.condition = stringExpression;
    expect(() => ifStatement.checkType([])).toThrow();
  });

  it('Should return correct statements', () => {
    const question = new QlQuestion('question', '', new IntQuestionType(), emptyLoc);
    const elseQuestion = new QlQuestion('elseQuestion', '', new IntQuestionType(), emptyLoc);
    const ifStatement = new If(null, [question], [elseQuestion], emptyLoc);

    const statements = ifStatement.getQuestions();
    expect(statements.length).toBe(2);
    expect(statements[0].name).toBe('question');
    expect(statements[1].name).toBe('elseQuestion');
  });

  it('Should return variables of sub-ifs', () => {
    const expression = new Variable('expression', emptyLoc);
    const subExpression = new Variable('subExpression', emptyLoc);
    const subIfStatement = new If(expression, [], [], emptyLoc);
    const ifStatement = new If(subExpression, [subIfStatement], [], emptyLoc);

    const variables: Variable[] = ifStatement.getVariables();

    expect(variables.length).toBe(2);
    expect(variables[0].identifier).toBe('expression');
    expect(variables[1].identifier).toBe('subExpression');
  });
});
