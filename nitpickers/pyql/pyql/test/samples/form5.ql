form Box1HouseOwning {
   housePrice: "Did you sell a house in 2010?" integer
   houseInhabitants: "Did you buy a house in 2010?" decimal
   if (housePrice / houseInhabitants) {
      sellingPrice: "Price the house was sold for:" money
      privateDebt: "Private debts for the sold house:" money
      valueResidue: "Value residue:" money
    }
}