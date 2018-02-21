import {Form, Question, QuestionType} from './';
import {CheckboxQuestion} from '../angular-questions/question-checkbox';
import {TextboxQuestion} from '../angular-questions/question-textbox';
import {Location} from '../ast/index';

const emptyLoc: Location = {
  start:
    {
      offset: 0,
      line: 0,
      column: 0
    },
  end:
    {
      offset: 0,
      line: 0,
      column: 0
    }
};

const questions = [
  new Question('intQuestion', 'intQuestion?', QuestionType.INT, emptyLoc),
  new Question('decimalQuestion', 'decimalQuestion?', QuestionType.DECIMAL, emptyLoc),
  new Question('moneyQuestion', 'moneyQuestion?', QuestionType.MONEY, emptyLoc),
  new Question('booleanQuestion', 'booleanQuestion?', QuestionType.BOOLEAN, emptyLoc),
  new Question('stringQuestion', 'stringQuestion?', QuestionType.STRING, emptyLoc),
  new Question('dateQuestion', 'dateQuestion?', QuestionType.DATE, emptyLoc)
];

it('should return the proper QuestionBase questions', () => {
  const formQuestions = new Form('test', questions, emptyLoc).toFormQuestion();
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
