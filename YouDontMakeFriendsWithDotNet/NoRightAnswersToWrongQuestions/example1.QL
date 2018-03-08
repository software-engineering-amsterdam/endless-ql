form personalInfo {
  name: "Name: "  string
  dateOfBirth: "D.O.B." date
  childCount: "number of children:" integer
  currentBlance: "money in pocket:" decimal
  isHappy: "are you happy:" boolean
  if (isHappy && (currentBlance < 50))
  {
      shouldNotBeHappy: "Are you Sure you're happy?" boolean
  }
  if (!isHappy && (currentBalance >= 50))
  {
      shouldBeHappy: "Are you Sure you're unhappy?" boolean
  }
}