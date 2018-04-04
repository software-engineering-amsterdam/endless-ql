form Box1HouseOwning {
   hasSoldHouse: "Did you sell a house in 2010?" boolean
   hasBoughtHouse: "Did you buy a house in 2010?" boolean
   hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean(True + 3)
   if (hasSoldHouse) {
      sellingPrice: "Price the house was sold for:" money
      privateDebt: "Private debts for the sold house:" money
      valueResidue: "Value residue:" money(sellingPrice - age)
   } else {
      age: "What's your age?" integer
      license: "How old were you when you got your driving license?" integer
      experience: "You have been driving for: " integer(license - age)
      eligible: "Eligible for cheap insurance": boolean(age > 20 && experience > 7)
      if (age > 18) {
        age: "Duplicated age question" integer
      }
   }
   wrong: "Wrong question with some undefined variables" integer(sellingPrice + aaa + hasSoldHouse)
}