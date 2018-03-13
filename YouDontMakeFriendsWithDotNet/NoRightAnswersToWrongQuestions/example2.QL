form taxOfficeExample {
  "Enter Name: " victimName: string
  "Did you buy a house in last year?" hasBoughtHouse: boolean

  if (hasBoughtHouse) {
    "What was the price?" price: decimal
	"Did you take a loan?" hasMortgage: boolean

    if (hasMortgage) {
	    "how much was the loan?" mortgage: decimal
	}
    
	"your house had the following equity:" equity: money =(price - mortgage)
  }
}