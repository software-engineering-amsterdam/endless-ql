/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined, duplicate variables or nonexistent variables in conditions */
form taxOfficeExample {
    "Q1: Give me the word 'hello'?" someString: string
    if(someString == "hello") {
        "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" someBoolean3: decimal
    } else {
        "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" someBoolean3: decimal
    }
    "Q6: You can only answer this question if you answered Q5 > 5" someBoolean4: integer = someBoolean3
}