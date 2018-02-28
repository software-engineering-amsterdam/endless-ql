//Q=3
//otherPrice:=IntValue 1
form taxOfficeExample {
  "What was the selling price?"
      sellingPrice: decimal = 1.5 * 10

 "What was the costprice?"
     otherPrice: decimal

    if (sellingPrice + otherPrice == 16) {
      "Did you wait to long?"
        waitedToLong: boolean
    }

}