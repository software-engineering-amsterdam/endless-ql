import {Form, Question, QuestionType} from './';
import {BooleanQuestion} from '../angular-questions/boolean-question';
import {InputQuestion} from '../angular-questions/input-question';
import {Statement} from '../ast';
import {If} from './if';
import {FormControl, FormGroup} from '@angular/forms';
import {Variable} from './expressions/variable';
import * as astMock from './ast-mock';

describe('Form', () => {

  const questions: Statement[] = [
    new Question('intQuestion', 'intQuestion?', QuestionType.INT, astMock.emptyLoc),
    new Question('booleanQuestion', 'booleanQuestion?', QuestionType.BOOLEAN, astMock.emptyLoc),
    new Question('booleanQuestion2', 'booleanQuestion2?', QuestionType.BOOLEAN, astMock.emptyLoc),
    new Question('stringQuestion', 'stringQuestion?', QuestionType.STRING, astMock.emptyLoc),
    new Question('dateQuestion', 'dateQuestion?', QuestionType.DATE, astMock.emptyLoc)
  ];

  const ifs = [
    new If(new Variable('booleanQuestion', astMock.emptyLoc),
      [
        new If
        (new Variable('booleanQuestion2', astMock.emptyLoc),
          [new Question('intQuestion2', 'intQuestion2?', QuestionType.INT, astMock.emptyLoc)],
          [],
          astMock.emptyLoc)
      ],
      [],
      astMock.emptyLoc)
  ];

  it('should return the proper QuestionBase questions', () => {
    const formQuestions = new Form('test', questions, astMock.emptyLoc).toFormQuestion();
    expect(formQuestions.length).toBe(5);
    expect(formQuestions[0].key).toBe('intQuestion');
    expect(formQuestions[0].label).toBe('intQuestion?');
    expect(formQuestions[0].controlType).toBe('textbox');
    expect((<InputQuestion> formQuestions[0]).type).toBe('number');
    expect((<InputQuestion> formQuestions[1]).type).toBe('boolean');
    expect((<BooleanQuestion> formQuestions[2]).type).toBe('boolean');
    expect(formQuestions[2].controlType).toBe('checkbox');
    expect((<InputQuestion> formQuestions[3]).type).toBe('text');
    expect((<InputQuestion> formQuestions[4]).type).toBe('date');
  });

  it('should check condition of both ifs when nested', () => {
    const formQuestions = new Form('test', questions.concat(ifs), astMock.emptyLoc).toFormQuestion();

    expect(formQuestions.length).toBe(6);

    const group: any = {};
    group['booleanQuestion'] = new FormControl();
    group['booleanQuestion2'] = new FormControl();
    const formGroup = new FormGroup(group);

    formGroup.controls['booleanQuestion'].setValue(true);
    formGroup.controls['booleanQuestion2'].setValue(true);
    expect(formQuestions[5].hiddenCondition(formGroup)).toBe(true);

    formGroup.controls['booleanQuestion'].setValue(false);
    formGroup.controls['booleanQuestion2'].setValue(true);
    expect(formQuestions[5].hiddenCondition(formGroup)).toBe(false);

    formGroup.controls['booleanQuestion'].setValue(true);
    formGroup.controls['booleanQuestion2'].setValue(false);
    expect(formQuestions[5].hiddenCondition(formGroup)).toBe(false);

    formGroup.controls['booleanQuestion'].setValue(false);
    formGroup.controls['booleanQuestion2'].setValue(false);
    expect(formQuestions[5].hiddenCondition(formGroup)).toBe(false);
  });
});
