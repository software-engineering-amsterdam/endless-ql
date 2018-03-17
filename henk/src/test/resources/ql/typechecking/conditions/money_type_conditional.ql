form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: money

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
  }
}
