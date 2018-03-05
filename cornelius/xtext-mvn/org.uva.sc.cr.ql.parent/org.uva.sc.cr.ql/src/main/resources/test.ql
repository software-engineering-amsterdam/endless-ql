form Box1HouseOwning {
	"Did you sell a house in 2010?" hasSoldHouse: boolean
	"Did you by a house in 2010?" hasBoughtHouse: boolean
	"Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean
	"What is your first name?" firstName: string
	"What is your last name?" lastName: string
	"Integer: " int1: integer
	"Integer: " int2: integer
	"Integer MultiResult: " int3: integer = (int1 * int2)
	if (hasSoldHouse && hasBoughtHouse) {
		"Price the house was sold for:" sellingPrice: money
		"Private debts for the sold house:" privateDebt: money
		"Value residue:" valueResidue: money = (sellingPrice - privateDebt)
		"Name: " result: string = (firstName + " " + lastName)
	}
	if (result == "Cornelius Ries"){
		"Cornelius Ries, when did you sell the house?" sellDate: date
	}
}