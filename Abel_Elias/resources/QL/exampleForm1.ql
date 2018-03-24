form Box1HouseOwning {
       hasSoldHouse: "Did you sell a house in 2010?" boolean
       hasBoughtHouse: "Did you buy a house in 2010?" boolean
       // BEGIN CYLIC LINES
       hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean hasBoughtHouse
       hasBoughtHouse: "Did you enter a loan for maintenance/reconstruction?" boolean hasMaintLoan
       // END CYLIC LINES
       if (hasSoldHouse) {
           sellingPrice: "Price the house was sold for:" money
           // example line comment
           privateDebt: "Private debts for the sold house:" money /*
              example
              multiple
              line
              comment
           */
           valueResidue: "Value residue:" money (sellingPrice - privateDebt)
       }
   }