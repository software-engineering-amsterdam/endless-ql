<<<<<<< HEAD
/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined, duplicate variables or nonexistent variables in conditions */
form taxOfficeExample {
    someString: "Q1: Give me the word 'hello'?" string
    someBoolean1: "Q2: Give me true or false?" boolean
    if(someBoolean1) {
        someBoolean2: "Q3: You can only answer this question if you answered true above" boolean
        if(someString == "hello") {
            someBoolean3: "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" boolean
        }
    }
    someNumber: "Q5: Can you give me a number?" decimal
    if(someNumber > 5){
        someBoolean4: "Q6: You can only answer this question if you answered Q5 > 5" boolean
    } else {
        someBoolean5: "Q7: This will only enable if someNumber <= 5" boolean
    }
    someNumberWithExpression: "Some text here" decimal = ((someNumber + 2) * 2)
=======
/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined, duplicate variables or nonexistent variables in conditions */
form taxOfficeExample {
    someString: "Q1: Give me the word 'hello'?" string
    someBoolean1: "Q2: Give me true or false?" boolean
    if(someBoolean1) {
        someBoolean2: "Q3: You can only answer this question if you answered true above" boolean
        if(someString == "hello") {
            someBoolean3: "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals 'hello'" boolean
        }
    }
    someNumber: "Q5: Can you give me a number?" decimal
    if(someNumber > 5){
        someBoolean4: "Q6: You can only answer this question if you answered Q5 > 5" boolean
    }
    someNumberWithExpression: "Some text here" decimal = ((someNumber + 2) * 2)
>>>>>>> 3c171d077d7945c6cc73b62beb833d1ee457800c
}