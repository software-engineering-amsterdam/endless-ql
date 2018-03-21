form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean

  if (firstUndeclaredIdentifier) {
    "What was the selling price?"
      sellingPrice: money
  }

  if (secondUndeclaredIdentifier) {
    "What was the bying price?"
      buyingPrice: money
  }
}
