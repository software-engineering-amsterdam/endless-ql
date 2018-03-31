form Box1HouseOwning {
   buyingPrice: "How much did you pay for your house?" money
   hasBoughtHouse: "Did you by a house in 2010?" boolean
   if (!(buyingPrice > 30) && hasBoughtHouse) {
      sellingPrice: "Price the house was sold for:" money
      profitFactor: "test" decimal(buyingPrice / sellingPrice)
   }
}