/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined, duplicate variables or nonexistent variables in conditions */
form taxOfficeExample {
    "Q1: Give me the word 'hello'?" someString: string
    if(someString == "hello") {
        "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" someDecimal: decimal
    } else {
        "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" someDecimal: decimal
    }
    "Q6: You can only answer this question if you answered Q5 > 5" someInteger: integer = someDecimal

    "Can you give me an integer value?" someInteger_: integer
    "Can you give me a yes or no value?" someBoolean_: boolean
    "Can you give me a decimal value?" someDecimal_: decimal
    "Can you give me a date value?" someDate_: date
    "Can you give me a money value?" someMoney_: money
    "Can you give me a string value?" someString_: string
}