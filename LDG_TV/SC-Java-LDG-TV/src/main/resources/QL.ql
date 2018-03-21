form taxOfficeExample
{
  "What is the amount of mandatory courses?"
      amountMandatoryCourses: money
  "Did you attend all courses?"
      hasAttendCourses: boolean

  if (hasAttendCourses) {
    "How many courses did you pass?"
      passedCourses: money
    "How many ECTS did you earn per course?"
      ECTSPerCourse: money
    "Earned ECTS?"
      earnedECTS: money = (passedCourses * ECTSPerCourse)
  }
  "Did you apply correctly for your study?"
    applyCorrectly: boolean


  if(hasAttendCourses && applyCorrectly){
      "Congratulations you passed your masters"
          passedMaster: boolean
  }else{
      "You failed to pass or attend correcctly"
        failedMaster: boolean
  }
}