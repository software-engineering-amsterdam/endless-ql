form testForm {
    q1: "View the questions?" boolean
    if(q1){
        q2: "What is your name?" string = "Mr. Sugarnips"
        q3: "What is your favorite number?" integer
        q4: "What is your birthdate?" date
        q5: "Favorite number between 0 and 1?" decimal
        q6: "How much money do you have?" money
        q7: "How much money do you want" money
        q8: "You need this much: " money = (q7 - q6)
    }
}