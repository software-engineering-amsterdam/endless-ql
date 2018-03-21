import {BooleanLiteral} from '../expressions/literals/boolean-literal';
import {ExpressionType} from '../expressions/expression-type';
import {AndExpression, OrExpression} from '../expressions/logical-expression';
import {AddExpression, DivideExpression, MultiplyExpression, SubtractExpression} from '../expressions/arithmetic-expression';
import {NegateExpression, NegativeExpression} from '../expressions/unary-expression';
import {Location} from '../../location';
import {
  GreaterThanEqualExpression, GreaterThanExpression, LessThanEqualExpression,
  LessThanExpression
} from '../expressions/comparison-expression';
import {EqualExpression, UnequalExpression} from '../expressions/equality-expression';
import {AbstractControl, FormControl, FormGroup} from '@angular/forms';
import {Variable} from '../expressions/variable';
import {DateLiteral, NumberLiteral, StringLiteral} from '../';
import {EvaluateExpressionVisitor} from './evaluate-expression-visitor';
import {CheckExpressionTypeVisitor} from './check-expression-type-visitor';

const location: Location = {
  start: {
    offset: 0,
    line: 0,
    column: 0
  },
  end: {
    offset: 0,
    line: 0,
    column: 0
  }
};
const stringLiteral = new StringLiteral('string', location);
const dateLiteral = new DateLiteral(new Date('01-01-1990'), location);
const booleanLiteral = new BooleanLiteral(true, location);
const intLiteral = new NumberLiteral(5, location);
const secondIntLiteral = new NumberLiteral(8, location);

const andExpression = new AndExpression(booleanLiteral, booleanLiteral,  location);
const orExpression = new OrExpression(booleanLiteral, booleanLiteral,  location);

const timesExpression = new MultiplyExpression(intLiteral, secondIntLiteral, location);
const divideExpression = new DivideExpression(intLiteral, intLiteral, location);
const addExpression = new AddExpression(secondIntLiteral, intLiteral, location);
const subtractExpression = new SubtractExpression(secondIntLiteral, secondIntLiteral,  location);
const lessThanExpression = new LessThanExpression(intLiteral, secondIntLiteral, location);
const greaterThanExpression = new GreaterThanExpression(intLiteral, intLiteral,  location);
const lessEqualExpression = new LessThanEqualExpression(secondIntLiteral, intLiteral,  location);
const greaterEqualExpression = new GreaterThanEqualExpression(secondIntLiteral, secondIntLiteral, location);

const equalExpression = new EqualExpression(intLiteral, intLiteral, location);
const unequalExpression = new UnequalExpression(booleanLiteral, booleanLiteral,  location);

const negativeExpression = new NegativeExpression(intLiteral, location);
const negateExpression = new NegateExpression(booleanLiteral, location);

const variableExpression = new Variable('booleanQuestion', location);

describe('Expressions', () => {
  describe('should evaluate', () => {
    const form = new FormGroup({booleanQuestion: new FormControl({value: ''})});
    it('literals', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, stringLiteral).getValue()).toBe('string');
      expect(EvaluateExpressionVisitor.evaluate(form, dateLiteral).getValue()).toEqual(new Date('01-01-1990'));
      expect(EvaluateExpressionVisitor.evaluate(form, booleanLiteral).getValue()).toBe(true);
      expect(EvaluateExpressionVisitor.evaluate(form, intLiteral).getValue()).toBe(5);
    });

    it('logical expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, andExpression).getValue()).toBe(true);
      expect(EvaluateExpressionVisitor.evaluate(form, orExpression).getValue()).toBe(true);
    });

    it('arithmetic expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, timesExpression).getValue()).toBe(40.0);
      expect(EvaluateExpressionVisitor.evaluate(form, divideExpression).getValue()).toBe(1);
      expect(EvaluateExpressionVisitor.evaluate(form, addExpression).getValue()).toBe(13.0);
      expect(EvaluateExpressionVisitor.evaluate(form, subtractExpression).getValue()).toBe(0.0);
    });

    it('comparison expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, lessThanExpression).getValue()).toBe(true);
      expect(EvaluateExpressionVisitor.evaluate(form, greaterThanExpression).getValue()).toBe(false);
      expect(EvaluateExpressionVisitor.evaluate(form, lessEqualExpression).getValue()).toBe(false);
      expect(EvaluateExpressionVisitor.evaluate(form, greaterEqualExpression).getValue()).toBe(true);
    });

    it('equality expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, equalExpression).getValue()).toBe(true);
      expect(EvaluateExpressionVisitor.evaluate(form, unequalExpression).getValue()).toBe(false);
    });

    it('unary expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, negativeExpression).getValue()).toBe(-5);
      expect(EvaluateExpressionVisitor.evaluate(form, negateExpression).getValue()).toBe(false);
    });

    it('variable expressions', () => {
      expect(EvaluateExpressionVisitor.evaluate(form, variableExpression).getValue()).toBeUndefined();

      form.controls['booleanQuestion'].setValue(true);
      expect(EvaluateExpressionVisitor.evaluate(form, variableExpression).getValue()).toBe(true);

      const unknownIdentifierVariableExpression = new Variable('identifier', location);
      expect(() => EvaluateExpressionVisitor.evaluate(form, unknownIdentifierVariableExpression).getValue()).toThrow();
    });
  });
  describe('Should check and return type', () => {
    it('logical expressions', () => {
      expect(CheckExpressionTypeVisitor.evaluate(andExpression)).toBe(ExpressionType.BOOLEAN);
      expect(() => CheckExpressionTypeVisitor.evaluate(new AndExpression(intLiteral, booleanLiteral, location)
      )).toThrowError();
    });

    it('arithmetic expressions', () => {
      expect(CheckExpressionTypeVisitor.evaluate(timesExpression)).toBe(ExpressionType.NUMBER);
      expect(() => CheckExpressionTypeVisitor.evaluate(new DivideExpression(intLiteral, booleanLiteral, location)
        )).toThrowError();
    });

    it('comparison expressions', () => {
      expect(CheckExpressionTypeVisitor.evaluate(lessThanExpression)).toBe(ExpressionType.BOOLEAN);
      expect(() => CheckExpressionTypeVisitor.evaluate(new GreaterThanExpression(intLiteral, booleanLiteral, location)
        )).toThrowError();
    });

    it('equality expressions', () => {
      expect(CheckExpressionTypeVisitor.evaluate(equalExpression)).toBe(ExpressionType.BOOLEAN);
      expect(() => CheckExpressionTypeVisitor.evaluate(new EqualExpression(intLiteral, booleanLiteral, location)
        )).toThrowError();
    });

    it('unary expressions', () => {
      expect(CheckExpressionTypeVisitor.evaluate(negativeExpression)).toBe(ExpressionType.NUMBER);
      expect(CheckExpressionTypeVisitor.evaluate(negateExpression)).toBe(ExpressionType.BOOLEAN);
      expect(() => CheckExpressionTypeVisitor.evaluate(new NegativeExpression(booleanLiteral,  location)
        )).toThrowError();
      expect(() => CheckExpressionTypeVisitor.evaluate(new NegateExpression(secondIntLiteral, location)
        )).toThrowError();
    });

    it('expressions should type check input literals', () => {
      const literalArray = [stringLiteral, dateLiteral, booleanLiteral, intLiteral, secondIntLiteral];

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].checkType([]) === ExpressionType.BOOLEAN && literalArray[j].checkType([]) === ExpressionType.BOOLEAN) {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new AndExpression(literalArray[i], literalArray[j], location));
            }).not.toThrow();
          } else {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new AndExpression(literalArray[i], literalArray[j],  location));
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].checkType([]) === ExpressionType.NUMBER && literalArray[j].checkType([]) === ExpressionType.NUMBER) {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new AddExpression(literalArray[i], literalArray[j],  location));
            }).not.toThrow();
          } else {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new AddExpression(literalArray[i], literalArray[j],  location));
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].checkType([]) === ExpressionType.NUMBER && literalArray[j].checkType([]) === ExpressionType.NUMBER) {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new LessThanExpression(literalArray[i], literalArray[j],  location));
            }).not.toThrow();
          } else {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new LessThanExpression(literalArray[i], literalArray[j],  location));
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].checkType([]) === literalArray[j].checkType([])) {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new EqualExpression(literalArray[i], literalArray[j],  location));
            }).not.toThrow();
          } else {
            expect(() => {
              CheckExpressionTypeVisitor.evaluate(new EqualExpression(literalArray[i], literalArray[j], location));
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        if (literalArray[i].checkType([]) === ExpressionType.NUMBER) {
          expect(() => {
            CheckExpressionTypeVisitor.evaluate(new NegativeExpression(literalArray[i], location));
          }).not.toThrow();
        } else {
          expect(() => {
            CheckExpressionTypeVisitor.evaluate(new NegativeExpression(literalArray[i], location));
          }).toThrow();
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        if (literalArray[i].checkType([]) === ExpressionType.BOOLEAN) {
          expect(() => {
            CheckExpressionTypeVisitor.evaluate(new NegateExpression(literalArray[i],  location));
          }).not.toThrow();
        } else {
          expect(() => {
            CheckExpressionTypeVisitor.evaluate(new NegateExpression(literalArray[i],  location));
          }).toThrow();
        }
      }
    });
  });
});
