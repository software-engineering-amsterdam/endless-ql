form Box1HouseOwning {
           hasSoldHouse: "Did you sell a house in 2010?" boolean
           hasBoughtHouse: "Did you buy a house in 2010?" boolean
           hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
           if (hasSoldHouse) {
               sellingPrice: "Price the house was sold for:" string
               privateDebt: "Private debts for the sold house:" string
               valueResidue: "Value residue:" string (sellingPrice)
           }
       }