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

export const formWrongQuestionName =
  `
    form form {
      questionå: "Question?" boolean
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
