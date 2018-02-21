//Q=2
form taxOfficeExample {
  "What was the selling price?"
    sellingPrice: decimal = 1.5

  "How many days did you wait?"
    waitDays: integer

    if (sellingPrice * waitDays > 50) {
      "Did you wait to long?"
        waitedToLong: boolean = true
    }

    if ((sellingPrice * waitDays) <= 50) {
      "Did you wait to long?"
        waitedToLong: boolean = false
    }


}