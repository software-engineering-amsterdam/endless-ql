/* This is some comment*/
form taxOfficeExample {
  someInteger: "Can you give me an integer value?" integer
  someBoolean: "Can you give me a yes or no value?" boolean
  someDecimal: "Can you give me a decimal value?" decimal
  someDate: "Can you give me a date value?" date
  someMoney: "Can you give me a money value?" money
  someString: "Can you give me a string value?" string

  someExpressionResult: "What is A + 1 + 2?" integer = (someInteger + 1 + 2)
  if(someBoolean) {
  	someBoolean2: "Does the date equal 1 January 2019?" boolean = (someDate == 1-1-2019)
  }
}