form Box1HouseOwning {
     hasSoldHouse: "Did you sell a house in 2010?" boolean
     hasBoughtHouse: "Did you buy a house in 2010?" boolean
     hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
     if (hasSoldHouse) {
         sellingPrice: "Price the house was sold for:" money
         sellingPrice: "Duplication of same type" money 50.00
         privateDebt: "Private debts for the sold house:" money
         privateDebt: "Duplication of different type:" bool true
         // Error should only be thrown for sellingPrice
         valueResidue: "Value residue:" money (sellingPrice - 50.0)
     }
 }