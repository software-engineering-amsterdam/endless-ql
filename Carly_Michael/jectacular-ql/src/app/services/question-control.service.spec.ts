import {inject, TestBed} from '@angular/core/testing';
import {QuestionControlService} from './question-control.service';
import {TextboxQuestion} from '../domain/question-textbox';
import {CheckboxQuestion} from '../domain/question-checkbox';

describe('The question control service', () => {
  let service: QuestionControlService;

  const questions = [
    new TextboxQuestion({
      key: 'intQuestion',
      label: 'intQuestion?',
      type: 'number',
      value: undefined,
      order: 0
    }),
    new TextboxQuestion({
      key: 'decimalQuestion',
      label: 'decimalQuestion?',
      type: 'number',
      value: undefined,
      order: 1
    }),
    new TextboxQuestion({
      key: 'moneyQuestion',
      label: 'moneyQuestion?',
      type: 'number',
      value: undefined,
      order: 2
    }),
    new CheckboxQuestion({
      key: 'booleanQuestion',
      label: 'booleanQuestion?',
      type: 'boolean',
      value: undefined,
      order: 3
    }),
    new TextboxQuestion({
      key: 'stringQuestion',
      label: 'stringQuestion?',
      type: 'text',
      value: '',
      order: 4
    }),
    new TextboxQuestion({
      key: 'dateQuestion',
      label: 'dateQuestion?',
      type: 'date',
      value: undefined,
      order: 5
    })
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QuestionControlService]
    });
  });

  beforeEach(inject([QuestionControlService], (_service: QuestionControlService) => {
    service = _service;
  }));

  it('should return formControl elements for questions', () => {
    const result = service.toFormGroup(questions);
    expect(result.get('intQuestion')).toBeDefined();
    expect(result.get('decimalQuestion')).toBeDefined();
    expect(result.get('moneyQuestion')).toBeDefined();
    expect(result.get('booleanQuestion')).toBeDefined();
    expect(result.get('stringQuestion')).toBeDefined();
    expect(result.get('stringQuestion').value).toBe('');
    expect(result.get('dateQuestion')).toBeDefined();
  });
});


