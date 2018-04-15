form passingform {

   "View the questions?" qq: boolean
    if(qq){
        "What is the number of life?" q1: integer
        "Where is my mind?" q2: string
        "ready to see the answear?" q4: boolean
        if(q4){
            "Your right if you said pixies" q3: boolean
        }
         "This is a number larger than 5" q5: integer = 6
        if(q5 > 5){
            "If the number is larger than 5" q6: boolean = true
        }
        "This number is 3" q7: integer = 3
        if(q7 < 2){
            "This condition should fail:" q8: string
        }
        else{
           "q6 condition faild:" q9: string
        }
    }
}