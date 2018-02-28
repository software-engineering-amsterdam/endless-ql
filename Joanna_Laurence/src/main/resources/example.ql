form taxOfficeExample {
  "What was the selling price?"
    sellingPrice: decimal = 1.5

  "How many days did you wait?"
    waitDays: integer = 5

    if (sellingPrice * waitDays > 5) {
      "Did you wait to long?"
        waitedToLong: boolean
    }

    if (sellingPrice * waitDays > 15) {
      "Do you want to have a free giftbag?"
        freeBag: boolean
    }

  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
}