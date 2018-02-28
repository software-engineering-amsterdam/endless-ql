//Q=3
form taxOfficeExample {
  "What was the selling price?"
    sellingPrice: decimal = 1.5

  "How many days did you wait?"
    waitDays: integer = 5

    if (sellingPrice * waitDays > 50) {
      "Did you wait too long?"
        waitedTooLong: boolean = true
    }

    if (waitedTooLong) {
      "Did you wait too long?"
        waitedTooLong3: boolean
    }
}