/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined, duplicate variables or nonexistent variables in conditions */
form taxOfficeExample {
    "Q1: Give me the word 'hello'?" someString: string
    "Q2: Give me true or false?" someBoolean1: boolean
    if(someBoolean1) {
        "Q3: You can only answer this question if you answered true above" someBoolean2: boolean
        if(someString == "hello") {
            "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" someBoolean3: boolean
        } else {
            "Q4: You can only answer this question if you Q1 equals 1 and Q2 does not equal 'hello'" someBoolean9: boolean
        }
    }
    // TODO must be unsettable in GUI
    "Q5: Can you give me a number?" someNumber: integer
    if(someNumber > 5){
        "Q6: You can only answer this question if you answered Q5 > 5" someBoolean4: boolean
    } else {
        "Q7: This will only enable if someNumber <= 5" someBoolean5: boolean
    }
    "Some text here" someNumberWithExpression: decimal = ((someNumber + 2) * 2)
    "Give me a date value please?" someDate: date
    "Give me an integer value please?" someInteger: integer
    "Give me an money value please?" someMoney: money
    "Give me an decimal value please?" someDecimal: decimal
}