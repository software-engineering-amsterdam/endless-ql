form TestForm {
	"Did you sell a house in 2010?"
		soldHouse-In2010: boolean

	if(soldHouseIn2010 < 10.0) {
		"Have you bought a house in 2010?"
			boughtAHouseIn2010: boolean
	}
	
	"What was the selling price?"
		sellingPrice: money

	"For what price have you bought the house?"
		buyingPrice: money

	// A comment which will be ignored, right?
	if (soldHouseIn2010) {
		"Have you seen House of Cards?"
			seenHouseOfCards: boolean
	}

	if soldHouseIn2010 && true || false {
	}

	if soldHouseIn2010 && sellingPrice < sellingPrice * 10 - buyingPrice {
		"Ik wil kaas!" 
			wantsCheese: boolean
	}
		"Diff:"
			diff: money = buyingPrice - 10.0

	"What is your name?"
		name: text		

	"What is your middle name?"
		middlename: text		

	"What is your lastname?"
		lastname: text		

	"Fullname"
		fullName: text = name + middlename + lastname
}