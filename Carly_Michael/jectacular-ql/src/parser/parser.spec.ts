import {parse} from './ql-parser';

const simpleForm =
  `
    form form {
      question: "Question?" boolean
    }
  `;

const multipleQuestionForm =
  `
    form form {
      questionOne: "Question1?" boolean
      questionTwo: "Question2?" string
      questionThree: "Question3?" date
      questionFour: "Question4?" money
    }
  `;

const formWrongName =
  `
    form form! {
      question: "Question?" boolean
    }
  `;

const formWrongQuestionName =
  `
    form form {
      questionå: "Question?" boolean
    }
  `;

describe('The parser', () => {
  it('Should parse simple form', function() {
    const output = parse(simpleForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(1);
    expect(output.statements[0].name).toBe('question');
    expect(output.statements[0].label).toBe('Question?');
  });

  it('Should parse form only with certain characters', function() {
    expect(() => parse(formWrongName, {})).toThrow();
    expect(() => parse(formWrongQuestionName, {})).toThrow();
  });

  it('Should parse multiple questions', function() {
    const output = parse(multipleQuestionForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(4);
    expect(output.statements[0].name).toBe('questionOne');
    expect(output.statements[0].type).toBe('boolean');
    expect(output.statements[0].label).toBe('Question1?');
    expect(output.statements[1].name).toBe('questionTwo');
    expect(output.statements[1].label).toBe('Question2?');
    expect(output.statements[2].name).toBe('questionThree');
    expect(output.statements[2].label).toBe('Question3?');
    expect(output.statements[3].name).toBe('questionFour');
    expect(output.statements[3].label).toBe('Question4?');
  });
});
