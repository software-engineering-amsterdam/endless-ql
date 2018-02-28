import {Form, Question, QuestionType} from './';
import {CheckboxQuestion} from '../angular-questions/question-checkbox';
import {TextboxQuestion} from '../angular-questions/question-textbox';
import {Location, Statement} from '../ast/index';
import {If} from './if';
import {FormControl, FormGroup} from '@angular/forms';

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

const questions: Statement[] = [
  new Question('intQuestion', 'intQuestion?', QuestionType.INT, emptyLoc),
  new Question('decimalQuestion', 'decimalQuestion?', QuestionType.DECIMAL, emptyLoc),
  new Question('moneyQuestion', 'moneyQuestion?', QuestionType.MONEY, emptyLoc),
  new Question('booleanQuestion', 'booleanQuestion?', QuestionType.BOOLEAN, emptyLoc),
  new Question('booleanQuestion2', 'booleanQuestion2?', QuestionType.BOOLEAN, emptyLoc),
  new Question('stringQuestion', 'stringQuestion?', QuestionType.STRING, emptyLoc),
  new Question('dateQuestion', 'dateQuestion?', QuestionType.DATE, emptyLoc)
];

const ifs = [
  new If('booleanQuestion',
    [new If('booleanQuestion2',
      [new Question('intQuestion2', 'intQuestion2?', QuestionType.INT, emptyLoc)],
      emptyLoc)],
    emptyLoc)
];

it('should return the proper QuestionBase questions', () => {
  const formQuestions = new Form('test', questions, emptyLoc).toFormQuestion();
  expect(formQuestions.length).toBe(7);
  expect(formQuestions[0].key).toBe('intQuestion');
  expect(formQuestions[0].label).toBe('intQuestion?');
  expect(formQuestions[0].controlType).toBe('textbox');
  expect((<TextboxQuestion> formQuestions[0]).type).toBe('number');
  expect((<TextboxQuestion> formQuestions[1]).type).toBe('number');
  expect((<TextboxQuestion> formQuestions[2]).type).toBe('number');
  expect((<CheckboxQuestion> formQuestions[3]).type).toBe('boolean');
  expect(formQuestions[3].controlType).toBe('checkbox');
  expect((<TextboxQuestion> formQuestions[4]).type).toBe('boolean');
  expect((<TextboxQuestion> formQuestions[5]).type).toBe('text');
  expect((<TextboxQuestion> formQuestions[6]).type).toBe('date');
});

it('should check condition of both ifs when nested', () => {
  const formQuestions = new Form('test', questions.concat(ifs), emptyLoc).toFormQuestion();

  expect(formQuestions.length).toBe(8);

  const group: any = {};
  group['booleanQuestion'] = new FormControl();
  group['booleanQuestion2'] = new FormControl();
  const formGroup = new FormGroup(group);

  formGroup.controls['booleanQuestion'].setValue(true);
  formGroup.controls['booleanQuestion2'].setValue(true);
  expect(formQuestions[7].hiddenCondition(formGroup)).toBe(true);

  formGroup.controls['booleanQuestion'].setValue(false);
  formGroup.controls['booleanQuestion2'].setValue(true);
  expect(formQuestions[7].hiddenCondition(formGroup)).toBe(false);

  formGroup.controls['booleanQuestion'].setValue(true);
  formGroup.controls['booleanQuestion2'].setValue(false);
  expect(formQuestions[7].hiddenCondition(formGroup)).toBe(false);

  formGroup.controls['booleanQuestion'].setValue(false);
  formGroup.controls['booleanQuestion2'].setValue(false);
  expect(formQuestions[7].hiddenCondition(formGroup)).toBe(false);
});
