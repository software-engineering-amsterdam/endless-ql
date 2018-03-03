//Q=2
form taxOfficeExample {
  "What was the selling price?"
      sellingPrice: decimal = 1.5 * 10 + 1

    if (sellingPrice == 15) {
      "Did you wait to long?"
        waitedToLong: boolean

     "What was the costprice?"
         otherPrice: decimal

    } else {
             "What was the costprice?"
                 otherPrice2: decimal
    }

}