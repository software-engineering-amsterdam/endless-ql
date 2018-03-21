form Box3HouseOwning {
     hasSoldHouse: "1: Did you sell a  house in 2010?" boolean
     hasBoughtHouse: "2: Did you buy a house in 2010?" boolean
     hasMainLoan: "3: Did you enter a loan for maintenance/reconstruction?" boolean
     hasSecondLone: "4: Enter the amount" integer
     hasThirdLone: "5: Enter the text" string
     hasDate: "6: Enter the date" date
     hasMoney: "7: Enter the money" money
     if (hasSoldHouse) {
         sellingPrice: "1a: Price the house was sold for:" money
         privateDebt: "1b: Private debts for the sold house:" money
         valueResidue: "1c: Value residue:" money (sellingPrice - 50.0)
     }
     if(hasSecondLone > 5 || hasSecondLone == 2) {
        sellingPrice2: "2a: Price the house was sold for:" integer
        if(sellingPrice2 > 3) {
            sellingPrice4: "2b: Price the fourth house was sold for:" integer

        }
     } else if (hasSecondLone == 3) {
        sellingPrice3: "2c: Price the second house was sold for:" integer
     }
 }