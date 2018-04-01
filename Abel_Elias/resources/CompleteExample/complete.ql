form Box1HouseOwning {
     name: "Input your name" string
     age: "How old are you?" integer
     month: "In which month were you born?" string
     study: "Do you study at the UvA?" boolean
     if(study) {
         fav: "What is your favourite course?" string
         startDate: "When did you start studying?" date
     }
}