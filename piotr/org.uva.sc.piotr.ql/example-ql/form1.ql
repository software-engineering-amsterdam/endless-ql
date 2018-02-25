form taxOfficeExample {
  "What is your first name?"
    firstName: string

  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean




  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money(PLN)
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }

  if (hasSoldHouse || hasBoughtHouse) {
    "How much?"
        anotherSellingPrice: money(PLN)
  }

  if (firstName == "piotr") {

    "what is tour last name?"
        lastName: string

  }

  if (firstName + "piotr") {

      "what is tour last name?"
          anotherLastName: string

    }


}