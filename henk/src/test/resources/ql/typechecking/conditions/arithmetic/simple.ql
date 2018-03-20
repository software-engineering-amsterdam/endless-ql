form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: integer

  if (hasSoldHouse < (1+1)) {
    "What was the selling price?"
      sellingPrice: money
  }
}
