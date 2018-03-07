import {Literal} from './expression';
import {ExpressionType} from './expression-type';
import {LogicalExpression} from './logical-expression';
import {AddExpression, ArithmeticExpression, DivideExpression, MultiplyExpression, SubtractExpression} from './arithmetic-expression';
import {UnaryExpression} from './unary-expression';
import {Location} from '../location';
import {ComparisonExpression} from './comparison-expression';
import {EqualityExpression} from './equality-expression';
import {AbstractControl, FormControl, FormGroup} from '@angular/forms';
import {Variable} from './variable';

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
const stringLiteral = new Literal(ExpressionType.STRING, 'string', location);
const dateLiteral = new Literal(ExpressionType.DATE, new Date('01-01-1990'), location);
const booleanLiteral = new Literal(ExpressionType.BOOLEAN, true, location);
const intLiteral = new Literal(ExpressionType.NUMBER, 5, location);
const decimalLiteral = new Literal(ExpressionType.NUMBER, 8.0, location);

const andExpression = new LogicalExpression(booleanLiteral, booleanLiteral, '&&', location);
const orExpression = new LogicalExpression(booleanLiteral, booleanLiteral, '||', location);

const timesExpression = new MultiplyExpression(intLiteral, decimalLiteral, location);
const divideExpression = new DivideExpression(intLiteral, intLiteral, location);
const addExpression = new AddExpression(decimalLiteral, intLiteral, location);
const subtractExpression = new SubtractExpression(decimalLiteral, decimalLiteral,  location);
const lessThanExpression = new ComparisonExpression(intLiteral, decimalLiteral, '<', location);
const greaterThanExpression = new ComparisonExpression(intLiteral, intLiteral, '>', location);
const lessEqualExpression = new ComparisonExpression(decimalLiteral, intLiteral, '<=', location);
const greaterEqualExpression = new ComparisonExpression(decimalLiteral, decimalLiteral, '>=', location);

const equalExpression = new EqualityExpression(intLiteral, intLiteral, '==', location);
const inEqualExpression = new EqualityExpression(booleanLiteral, booleanLiteral, '!=', location);

const negativeExpression = new UnaryExpression(intLiteral, '-', location);
const negateExpression = new UnaryExpression(booleanLiteral, '!', location);

const variableExpression = new Variable('booleanQuestion', location);

describe('Expressions', () => {
  describe('should evaluate', () => {
    const form = new FormGroup({booleanQuestion: new FormControl({value: ''})});
    it('literals', () => {
      expect(stringLiteral.evaluate(form)).toBe('string');
      expect(dateLiteral.evaluate(form)).toEqual(new Date('01-01-1990'));
      expect(booleanLiteral.evaluate(form)).toBe(true);
      expect(intLiteral.evaluate(form)).toBe(5);
    });

    it('logical expressions', () => {
      expect(andExpression.evaluate(form)).toBe(true);
      expect(orExpression.evaluate(form)).toBe(true);
    });

    it('arithmetic expressions', () => {
      expect(timesExpression.evaluate(form)).toBe(40.0);
      expect(divideExpression.evaluate(form)).toBe(1);
      expect(addExpression.evaluate(form)).toBe(13.0);
      expect(subtractExpression.evaluate(form)).toBe(0.0);
    });

    it('comparison expressions', () => {
      expect(lessThanExpression.evaluate(form)).toBe(true);
      expect(greaterThanExpression.evaluate(form)).toBe(false);
      expect(lessEqualExpression.evaluate(form)).toBe(false);
      expect(greaterEqualExpression.evaluate(form)).toBe(true);
    });

    it('equality expressions', () => {
      expect(equalExpression.evaluate(form)).toBe(true);
      expect(inEqualExpression.evaluate(form)).toBe(false);
    });

    it('unary expressions', () => {
      expect(negativeExpression.evaluate(form)).toBe(-5);
      expect(negateExpression.evaluate(form)).toBe(false);
    });

    it('variable expressions', () => {
      expect(variableExpression.evaluate(form)).toBeUndefined();

      form.controls['booleanQuestion'].setValue(true);
      expect(variableExpression.evaluate(form)).toBe(true);

      const unknownIdentifierVariableExpression = new Variable('identifier', location);
      expect(() => unknownIdentifierVariableExpression.evaluate(form)).toThrow();
    });
  });
  describe('Should check and return type', () => {
    it('logical expressions', () => {
      expect(andExpression.checkType([])).toBe(ExpressionType.BOOLEAN);
      expect(() => new LogicalExpression(intLiteral, booleanLiteral, '&&', location)
        .checkType([])).toThrowError();
    });

    it('arithmetic expressions', () => {
      expect(timesExpression.checkType([])).toBe(ExpressionType.NUMBER);
      expect(() => new DivideExpression(intLiteral, booleanLiteral, location)
        .checkType([])).toThrowError();
    });

    it('comparison expressions', () => {
      expect(lessThanExpression.checkType([])).toBe(ExpressionType.BOOLEAN);
      expect(() => new ComparisonExpression(intLiteral, booleanLiteral, '>', location)
        .checkType([])).toThrowError();
    });

    it('equality expressions', () => {
      expect(equalExpression.checkType([])).toBe(ExpressionType.BOOLEAN);
      expect(() => new EqualityExpression(intLiteral, booleanLiteral, '==', location)
        .checkType([])).toThrowError();
    });

    it('unary expressions', () => {
      expect(negativeExpression.checkType([])).toBe(ExpressionType.NUMBER);
      expect(negateExpression.checkType([])).toBe(ExpressionType.BOOLEAN);
      expect(() => new UnaryExpression(booleanLiteral, '-', location)
        .checkType([])).toThrowError();
      expect(() => new UnaryExpression(decimalLiteral, '!', location)
        .checkType([])).toThrowError();
    });

    it('expressions should type check input literals', () => {
      const literalArray = [stringLiteral, dateLiteral, booleanLiteral, intLiteral, decimalLiteral];

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].type === ExpressionType.BOOLEAN && literalArray[j].type === ExpressionType.BOOLEAN) {
            expect(new LogicalExpression(literalArray[i], literalArray[j], '&&', location).checkType([]));
          } else {
            expect(() => {
              new LogicalExpression(literalArray[i], literalArray[j], '&&', location).checkType([]);
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].type === ExpressionType.NUMBER && literalArray[j].type === ExpressionType.NUMBER) {
            expect(new AddExpression(literalArray[i], literalArray[j],  location).checkType([]));
          } else {
            expect(() => {
              new AddExpression(literalArray[i], literalArray[j],  location).checkType([]);
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].type === ExpressionType.NUMBER && literalArray[j].type === ExpressionType.NUMBER) {
            expect(new ComparisonExpression(literalArray[i], literalArray[j], '<', location).checkType([]));
          } else {
            expect(() => {
              new ComparisonExpression(literalArray[i], literalArray[j], '<', location).checkType([]);
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        for (let j = 0; j < literalArray.length; j++) {
          if (literalArray[i].type === literalArray[j].type) {
            expect(new EqualityExpression(literalArray[i], literalArray[j], '==', location).checkType([]));
          } else {
            expect(() => {
              new EqualityExpression(literalArray[i], literalArray[j], '==', location).checkType([]);
            }).toThrow();
          }
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        if (literalArray[i].type === ExpressionType.NUMBER) {
          expect(new UnaryExpression(literalArray[i], '-', location).checkType([]));
        } else {
          expect(() => {
            new UnaryExpression(literalArray[i], '-', location).checkType([]);
          }).toThrow();
        }
      }

      for (let i = 0; i < literalArray.length; i++) {
        if (literalArray[i].type === ExpressionType.BOOLEAN) {
          expect(new UnaryExpression(literalArray[i], '!', location).checkType([]));
        } else {
          expect(() => {
            new UnaryExpression(literalArray[i], '!', location).checkType([]);
          }).toThrow();
        }
      }
    });
  });
});
