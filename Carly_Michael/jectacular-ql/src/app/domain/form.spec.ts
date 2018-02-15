import {Form, Question, QuestionType} from './ast';
import {CheckboxQuestion} from './question-checkbox';
import {TextboxQuestion} from './question-textbox';

const questions = [
  new Question('intQuestion', 'intQuestion?', QuestionType.INT),
  new Question('decimalQuestion', 'decimalQuestion?', QuestionType.DECIMAL),
  new Question('moneyQuestion', 'moneyQuestion?', QuestionType.MONEY),
  new Question('booleanQuestion', 'booleanQuestion?', QuestionType.BOOLEAN),
  new Question('stringQuestion', 'stringQuestion?', QuestionType.STRING),
  new Question('dateQuestion', 'dateQuestion?', QuestionType.DATE)
];

it('should return the proper QuestionBase questions', () => {
  const formQuestions = new Form('test', questions).toFormQuestion();
  expect(formQuestions.length).toBe(6);
  expect(formQuestions[0].key).toBe('intQuestion');
  expect(formQuestions[0].label).toBe('intQuestion?');
  expect(formQuestions[0].controlType).toBe('textbox');
  expect((<TextboxQuestion> formQuestions[0]).type).toBe('number');
  expect((<TextboxQuestion> formQuestions[1]).type).toBe('number');
  expect((<TextboxQuestion> formQuestions[2]).type).toBe('number');
  expect((<CheckboxQuestion> formQuestions[3]).type).toBe('boolean');
  expect(formQuestions[3].controlType).toBe('checkbox');
  expect((<TextboxQuestion> formQuestions[4]).type).toBe('text');
  expect((<TextboxQuestion> formQuestions[5]).type).toBe('date');
});
