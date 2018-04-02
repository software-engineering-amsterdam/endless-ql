form Box1HouseOwning {
   age: "What's your age?" integer
   if (age > 18) {
      sellingPrice: "Price the house was sold for:" money
      privateDebt: "Private debts for the sold house:" money
      if (sellingPrice + privateDebt > $0) {
         bigQuestion: "What's your age?" string
      }
      valueResidue: "Value residue:" money(sellingPrice - privateDebt)
   }
   name: "What is your name?" string
}