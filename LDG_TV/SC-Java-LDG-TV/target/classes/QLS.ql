stylesheet taxOfficeExample
{
  page Courses
  {
    section "Amount"
    {
      question amountMandatoryCourses
        widget checkbox
    }
    section "Attendance"
    {
      question hasAttendCourses
        widget checkbox
      question applyCorrectly
        widget checkbox
    }
  }

  page ECTS
  { 
    section "Calculation"
    {
      question passedCourses
        widget radio("Yes", "No")
      question ECTSPerCourse
        widget spinbox
    }
      section "Progress"
      {
        question earnedECTS
          default money
          {
            width: 400
            font: "Arial"
            fontsize: 14
            color: #999999
              widget spinbox
            }
      }
  }
  page Diploma
  {
	section "Passed"
	{
    	question passedMaster
        widget checkbox
	}
    section "Failed"
    {
        question failedMaster
        widget checkbox
    }
  }
}