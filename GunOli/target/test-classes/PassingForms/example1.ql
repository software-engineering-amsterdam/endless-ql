form testForm {
    "View the questions?" q1: boolean
    if(q1){
        "Are you sure?" qq: boolean
        if(qq){
            "Do you want to give your birthdate?" qqq: boolean
            if(q1 && qq){
                "What is your name?" q2: string = "Mr. Sugarnips"
                "What is your name?" qw: string
                "What is your favorite number?" q3: integer
                "Favorite decimal (really..)?" q5: decimal
                "How much money do you have?" q6: money
                "How much money do you want" q7: money
                "You need this much:" q8: money = (q7 - q6)
            }
            if(qqq){
                "What is your birthdate?" q4: date
            } else {
                "You hate birthdays?" q9: boolean
            }
        }
    }
}