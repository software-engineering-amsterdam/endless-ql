form Box1HouseOwning {
    "Did you sell a house in 2010?" hasSoldHouse: boolean
    "Did you by a house in 2010?" hasBoughtHouse: boolean hasSoldHouse
    "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean
    if (hasSoldHouse) {
        "Price the house was sold for:" sellingPrice: money
        "Private debts for the sold house:" privateDebt: money
        "Value residue:" valueResidue: money (sellingPrice - privateDebt)
    } else {
    	"You don't have a house?" noHouse: boolean
    }
}