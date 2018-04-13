form passingform {
    qq: "View the questions?" boolean
    if(qq){
        q1: "What is the number of life?"  integer
        q2: "Where is my mind?" string
        q4: "ready to see the answear?" boolean
        if(q4){

            q3: "Your right if you said pixies" boolean
        }
        q5: "This is a number larger than 5" integer = 6
        if(q5 > 5){
            q6: "If the number is larger than 5" boolean = true
        }
        q7: "This number is 3" integer = 3
        if(q7 < 2){
            q8: "This condition should fail: " string
        }
        else{
            q9: "q6 condition faild:" string
        }
    }
}