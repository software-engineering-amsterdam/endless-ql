// mock input for form
export const validFormWithIf =
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

export const formWrongName =
  `
    form form! {
      question: "Question?" boolean
    }
  `;


export const formWrongQuestionName =
  `
    form form {
      questionå: "Question?" boolean
    }
  `;

export const formMissingQuestionName =
  `
    form form {
      : "Question?" boolean
      question: "Question?" boolean
    }
  `;

export const duplicateIdentifierForm =
  `
    form form {
      question1: "IntegerQuestion?"  integer
      question2: "DecimalQuestion?"  decimal
      question3: "MoneyQuestion?"  money
      question4: "BooleanQuestion?"  boolean
      question5: "StringQuestion?"  string
      question5: "StringQuestion?"  string
      question6: "DateQuestion?"  date
      if (question4) {
        question7: "ifQuestion" integer
      }
  }
  `;

export const ifQuestionForm =
  `
    form form {
      question: "Question?" boolean
      if (question) {
        questionIf: "QuestionIf?" integer
      }
    }
  `;

export const simpleForm =
  `
    form form {
      question: "Question?" boolean
    }
  `;

export const multipleQuestionForm =
  `
    form form {
      questionOne: "Question1?" boolean
      questionTwo: "Question2?" string
      questionThree: "Question3?" date
      questionFour: "Question4?" money
    }
  `;

export const expressionQuestionForm =
  `
    form form {
      question: "Question?" integer
      exprQuestion: "Expression?" integer = (10 + 500)
    }
  `;

export const expressionVariableForm =
  `
    form form {
      question: "Question?" integer
      exprQuestion: "Expression?" integer = question * 5
    }
  `;
