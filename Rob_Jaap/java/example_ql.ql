form Box1HouseOwning {
   hasSoldHouse: "Did you sell a house in 2010?" boolean
   hasBoughtHouse: "Did you buy a house in 2010?" boolean
   hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
   if (hasSoldHouse) {
 	sellingPrice: "Price the house was sold for:" money
 	privateDebt: "Private debts for the sold house:" money
 	valueResidue: "Value residue:" money(sellingPrice - privateDebt)
 	valueResidue2: "Value residue:" money(-true)
   }
 }

 form Box2HouseOwning {
    hasSoldHouse2: "Did you sell a house in 2010?" boolean
    hasBoughtHouse2: "Did you buy a house in 2010?" boolean
    hasMaintLoan2: "Did you enter a loan for maintenance/reconstruction?" boolean
  	sellingPrice2: "Price the house was sold for:" money
  	privateDebt2: "Private debts for the sold house:" money
  	valueResidue3: "Value residue:" money(sellingPrice - privateDebt)
  }