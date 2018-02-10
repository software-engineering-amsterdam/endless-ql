form Box1HouseOwning {
	hasSoldHouse: "Did you sell a house in 2010?" boolean
	hasBoughtHouse: "Did you by a house in 2010?" boolean
	hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
	firstName: "What is your first name?" string
	lastName: "What is your last name?" string
	int1: "Integer 1: " integer
	int2: "Integer 2: " integer
	int3: "Integer MultiResult: " integer = (int1 * int2)
	if (hasSoldHouse && hasBoughtHouse) {
		sellingPrice: "Price the house was sold for:" money
		privateDebt: "Private debts for the sold house:" money
		valueResidue: "Value residue:" money = (sellingPrice - privateDebt)
		result: "Name: " string = (firstName + " " + lastName)
	}
	if (result == "Cornelius Ries"){
		sellDate: "Cornelius Ries, when did you sell the house?" date
	}
}