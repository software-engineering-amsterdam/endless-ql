import {Literal} from './expression';
import {ExpressionType} from './expression-type';
import {LogicalExpression} from './logical-expression';
import {BinaryExpression} from './binary-expression';
import {UnaryExpression} from './unary-expression';
import {Location} from './location';

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

const timesExpression = new BinaryExpression(intLiteral, decimalLiteral, '*', location);
const divideExpression = new BinaryExpression(intLiteral, intLiteral, '/', location);
const addExpression = new BinaryExpression(decimalLiteral, intLiteral, '+', location);
const subtractExpression = new BinaryExpression(decimalLiteral, decimalLiteral, '-', location);

const negativeExpression = new UnaryExpression(intLiteral, '-', location);
const negateExpression = new UnaryExpression(booleanLiteral, '!', location);

describe('Expressions', () => {
  describe('should evaluate', () => {
    it('literals', () => {
      expect(stringLiteral.evaluate()).toBe('string');
      expect(dateLiteral.evaluate()).toEqual(new Date('01-01-1990'));
      expect(booleanLiteral.evaluate()).toBe(true);
      expect(intLiteral.evaluate()).toBe(5);
    });

    it('logical expressions', () => {
      expect(andExpression.evaluate()).toBe(true);
      expect(orExpression.evaluate()).toBe(true);
    });

    it('binary expressions', () => {
      expect(timesExpression.evaluate()).toBe(40.0);
      expect(divideExpression.evaluate()).toBe(1);
      expect(addExpression.evaluate()).toBe(13.0);
      expect(subtractExpression.evaluate()).toBe(0.0);
    });

    it('unary expressions', () => {
      expect(negativeExpression.evaluate()).toBe(-5);
      expect(negateExpression.evaluate()).toBe(false);
    });
  });
  describe('Should check and return type', () => {
    it('logical expressions', () => {
      expect(andExpression.checkType()).toBe(ExpressionType.BOOLEAN);
      expect(() => new LogicalExpression(intLiteral, booleanLiteral, '&&', location)
        .checkType()).toThrowError();
    });

    it('binary expressions', () => {
      expect(timesExpression.checkType()).toBe(ExpressionType.NUMBER);
      expect(() => new BinaryExpression(intLiteral, booleanLiteral, '/', location)
        .checkType()).toThrowError();
    });

    it('unary expressions', () => {
      expect(negativeExpression.checkType()).toBe(ExpressionType.NUMBER);
      expect(negateExpression.checkType()).toBe(ExpressionType.BOOLEAN);
      expect(() => new UnaryExpression(booleanLiteral, '-', location)
        .checkType()).toThrowError();
      expect(() => new UnaryExpression(decimalLiteral, '!', location)
        .checkType()).toThrowError();
    });
  });
});
