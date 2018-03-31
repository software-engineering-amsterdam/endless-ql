form testForm {
    q1: "View the questions?" boolean
    if(q1){
        qq: "Are you sure?" boolean
        if(qq){
            qqq: "Do you want to give your birthdate?" boolean
            qqw: "Do you care?" boolean
            if(q1 && qq){
                q2: "What is your name?" string = "Mr. Sugarnips"
                qw: "What is your name?" string
                q3: "What is your favorite number?" integer
                q5: "Favorite decimal (really..)?" decimal
                q6: "How much money do you have?" money
                q7: "How much money do you want" money
                q8: "You need this much: " money = (q7 - q6)
                test1: "test1" money = (q7 + q6)
                test2: "test2" money = (q7 * q6)
                test3: "test3" money = (q7 / q6)
                test4: "test4" boolean = (q7 > q6)
                test5: "test5" boolean = (q7 >= q6)
                test6: "test6" boolean = (q7 < q6)
                test7: "test7" boolean = (q7 <= q6)
                test8: "test8" boolean = (q7 == q6)
                test9: "test9" boolean = (q7 != q6)
                test10: "test10" boolean = (qqq || qqw)
                test11: "test11" money = (-q7)
                test12: "test12" boolean = (!qqw)
            }
            if(qqq){
                q4: "What is your birthdate?" date
            } else {
                q9: "You hate birthdays?" boolean
            }
        }
    }
}