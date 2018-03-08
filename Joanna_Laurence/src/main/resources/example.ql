form taxOfficeExample {
  "What was the selling price?"
    sellingPrice: integer

  "How many days did you wait?"
    waitDays: integer

    if (sellingPrice * waitDays > 5) {
      "Did you wait to long?"
        waitedToLong: boolean
    }

    if (sellingPrice * waitDays > 15) {
      "Do you want to have a free giftbag?"
        freeBag: boolean
    }

    if (freeBag) {
      "Do you want to have a second free giftbag?"
        freeBag2: boolean
      }

    if(freeBag2) {
        "Number of free giftbags: "
           nbfreeBags: integer = 2
        }
}