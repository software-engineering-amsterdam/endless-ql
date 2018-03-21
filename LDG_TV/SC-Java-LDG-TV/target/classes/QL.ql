form taxOfficeExample
{
  "What is the amount of mandatory courses?"
      amountMandatoryCourses: money
  "Did you apply correctly for your study?"
    applyCorrectly: boolean
  "Did you attend all courses?"
      hasAttendCourses: boolean

  if (hasAttendCourses && applyCorrectly) {
    "How many courses did you pass?"
      passedCourses: money
    "How many ECTS did you earn per course?"
      ECTSPerCourse: money
    "Earned ECTS?"
      earnedECTS: money = (passedCourses * ECTSPerCourse)
  }
}