// Identifier reference in both condition and computed question
form testForm {
    "First Question"
    someInteger : integer
    if (someInteger > 10) {
        "Second Question"
        computedInteger : integer = someInteger - 10
    }
}