import { TestBed, inject } from '@angular/core/testing';
import { QuestionService } from './question.service';
import {Question, QuestionType} from '../domain/ast';

describe('QuestionService', () => {
  let service: QuestionService;

  const questions = [
    new Question('intQuestion', 'intQuestion?', QuestionType.INT),
    new Question('decimalQuestion', 'decimalQuestion?', QuestionType.DECIMAL),
    new Question('moneyQuestion', 'moneyQuestion?', QuestionType.MONEY),
    new Question('booleanQuestion', 'booleanQuestion?', QuestionType.BOOLEAN),
    new Question('stringQuestion', 'stringQuestion?', QuestionType.STRING),
    new Question('dateQuestion', 'dateQuestion?', QuestionType.DATE)
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QuestionService]
    });
  });

  beforeEach(inject([QuestionService], (_service: QuestionService) => {
    service = _service;
  }));

  it('should return the proper QuestionBase questions', () => {
    const formQuestions = service.toFormQuestions(questions);
    expect(formQuestions.length).toBe(6);
    expect(formQuestions[0].key).toBe('intQuestion');
    expect(formQuestions[0].label).toBe('intQuestion?');
    expect(formQuestions[0].controlType).toBe('textbox');
    expect(formQuestions[0].type).toBe('number');
    expect(formQuestions[1].type).toBe('number');
    expect(formQuestions[2].type).toBe('number');
    expect(formQuestions[3].type).toBe('boolean');
    expect(formQuestions[3].controlType).toBe('checkbox');
    expect(formQuestions[4].type).toBe('text');
    expect(formQuestions[5].type).toBe('date');
  });
});
