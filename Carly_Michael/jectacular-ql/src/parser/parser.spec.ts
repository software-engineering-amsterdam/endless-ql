import {parse} from "./ql-parser";

const q1 = "form form {\n" +
  " \tquestion: \"Question?\"  boolean\n" +
  "}";

const q2 = "form form {\n" +
  " \tquestionOne: \"Question1?\"  boolean\n" +
  " \tquestionTwo: \"Question2?\"  string\n" +
  " \tquestionThree: \"Question3?\"  date\n" +
  " \tquestionFour: \"Question4?\"  money\n" +
  "}";

const wrongFormNameQ = "form form! {\n" +
  " \tquestion: \"Question?\"  boolean\n" +
  "}";

const wrongQuestionNameQ = "form form {\n" +
  " \tquestionå: \"Question?\"  boolean\n" +
  "}";

describe("The parser", () => {
  it("Should parse simple form", function() {
    const output = parse(q1, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe("form");
    expect(output.statements.length).toBe(1);
    expect(output.statements[0].name).toBe("question");
    expect(output.statements[0].label).toBe("Question?");
  });

  it("Should parse form only with certain characters", function() {
    expect(() => parse(wrongFormNameQ, {})).toThrow();
    expect(() => parse(wrongQuestionNameQ, {})).toThrow();
  });

  it("Should parse multiple questions", function() {
    const output = parse(q2, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe("form");
    expect(output.statements.length).toBe(4);
    expect(output.statements[0].name).toBe("questionOne");
    expect(output.statements[0].type).toBe('boolean');
    expect(output.statements[0].label).toBe("Question1?");
    expect(output.statements[1].name).toBe("questionTwo");
    expect(output.statements[1].label).toBe("Question2?");
    expect(output.statements[2].name).toBe("questionThree");
    expect(output.statements[2].label).toBe("Question3?");
    expect(output.statements[3].name).toBe("questionFour");
    expect(output.statements[3].label).toBe("Question4?");
  });
});
