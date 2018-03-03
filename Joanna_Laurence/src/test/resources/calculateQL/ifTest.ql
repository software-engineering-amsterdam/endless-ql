//Q=3
form taxOfficeExample {
  "What was the selling price?"
      sellingPrice: decimal = 1.5 * 10

    if (sellingPrice == 15.0) {
      "Did you wait to long?"
        waitedToLong: boolean

     "What was the costprice?"
         otherPrice: decimal

    } else {
             "What was the costprice?"
                 otherPrice2: decimal
    }

}