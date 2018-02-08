import {TestBed, inject} from '@angular/core/testing';

import {ParserService} from './parser.service';
import {QuestionType} from '../domain/ast';

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

  it('should be created', inject([ParserService], (service: ParserService) => {
    expect(service).toBeTruthy();
  }));

  it('should parse a valid ast to a form', () => {
    const result = service.parseInput(validInput);
    expect(result.name).toBe('form');
    expect(result.questions.length).toBe(6);
    expect(result.questions[0].name).toBe('question1');
    expect(result.questions[0].label).toBe('IntegerQuestion?');

    expect(result.questions[0].type).toBe(QuestionType.INT);
    expect(result.questions[1].type).toBe(QuestionType.DECIMAL);
    expect(result.questions[2].type).toBe(QuestionType.MONEY);
    expect(result.questions[3].type).toBe(QuestionType.BOOLEAN);
    expect(result.questions[4].type).toBe(QuestionType.STRING);
    expect(result.questions[5].type).toBe(QuestionType.DATE);
  });
});
