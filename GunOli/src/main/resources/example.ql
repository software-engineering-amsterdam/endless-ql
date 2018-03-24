form testForm {
    q1: "View the questions?" boolean
    if(q1){
        qq: "Are you sure?" boolean
        if(qq){
            qqq: "Do you want to give your birthdate?" boolean
            if(q1 && qq){
                q2: "What is your name?" string = "Mr. Sugarnips"
                q3: "What is your favorite number?" integer
                q5: "Favorite decimal (really..)?" decimal
                q6: "How much money do you have?" money
                q7: "How much money do you want" money
                q8: "You need this much: " money = (q7 - q6)
                q10: "Test" money = (q2 - q3)
            }
            if(qqq){
                q4: "What is your birthdate?" date
            } else {
                q9: "You hate birthdays?" boolean
            }
        }
    }
}