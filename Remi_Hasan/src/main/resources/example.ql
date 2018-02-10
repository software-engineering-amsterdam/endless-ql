/* This is some comment*/
/* TODO come up with cases with conditions where some value is undefined */
form taxOfficeExample {
    someNumber: "Q1: Give me some number?" decimal
    someBoolean: "Q2: Give me true or false?" boolean
    if(someBoolean) {
        someBoolean2: "Q3: You can only answer this question if you answered true above" boolean
        if(someNumber == 1.0) {
            someBoolean3: "Q4: You can only answer this question if you Q1 equals 1 and Q2 equals true" boolean
        }
    }
}