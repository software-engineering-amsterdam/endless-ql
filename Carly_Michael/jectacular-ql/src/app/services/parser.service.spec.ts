import {TestBed, inject} from '@angular/core/testing';

import {ParserService} from './parser.service';
import {Form, If, Question, QuestionType} from '../domain/ast';

describe('ParserService', () => {
  let service: ParserService;

  const validInput =
    `
      form form {
        question1: "IntegerQuestion?"  integer
        question2: "DecimalQuestion?"  decimal
        question3: "MoneyQuestion?"  money
        question4: "BooleanQuestion?"  boolean
        question5: "StringQuestion?"  string
 	      question6: "DateQuestion?"  date
 	      if (question4) {
 	        question7: "ifQuestion" integer
 	      }
      }
  `;

  const invalidInput =
    `
      form form {
 	      question: "Question?"  invalidType
      }
    `;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ParserService]
    });
  });

  beforeEach(inject([ParserService], (_service: ParserService) => {
    service = _service;
  }));

  it('should parse a valid input to a form', () => {
    const result = service.parseInput(validInput);
    console.log("result", result);
    expect(result.name).toBe('form');
    expect(result.statements.length).toBe(7);
    expect((<Question>result.statements[0]).name).toBe('question1');
    expect((<Question>result.statements[0]).label).toBe('IntegerQuestion?');

    expect((<Question>result.statements[0]).type).toBe(QuestionType.INT);
    expect((<Question>result.statements[1]).type).toBe(QuestionType.DECIMAL);
    expect((<Question>result.statements[2]).type).toBe(QuestionType.MONEY);
    expect((<Question>result.statements[3]).type).toBe(QuestionType.BOOLEAN);
    expect((<Question>result.statements[4]).type).toBe(QuestionType.STRING);
    expect((<Question>result.statements[5]).type).toBe(QuestionType.DATE);

    expect((<If>result.statements[6]).condition).toBe("question4");
    expect((<If>result.statements[6]).statements.length).toBe(1);
    expect((<Question>(<Form>result.statements[6]).statements[0]).type).toBe(QuestionType.INT);
    expect((<Question>(<Form>result.statements[6]).statements[0]).name).toBe("question7");
    expect((<Question>(<Form>result.statements[6]).statements[0]).label).toBe("ifQuestion");
  });

  it('should not parse an invalid input to a form', () => {
    expect(() => service.parseInput(invalidInput)).toThrowError();
  });
});
