form Box1HouseOwning {
	"What is your name?" name: string
    "Did you sell a house in 2010?" hasSoldHouse: boolean
    "Did you by a house in 2010?" hasBoughtHouse: boolean
    "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean
    if (1 + 1 == 2) {
        "Price the house was sold for:" sellingPrice: integer
        "Private debts for the sold house:" privateDebt: integer
        "Value residue:" valueResidue: integer (sellingPrice - privateDebt)
    } else {
    		"You don't have a house?" noHouse: boolean
    }
}