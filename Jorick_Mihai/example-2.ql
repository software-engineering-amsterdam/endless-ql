form Box1HouseOwning {
	"What is your name?" name: string
    "Did you sell a house in 2010?" hasSoldHouse: boolean
    "Did you by a house in 2010?" hasBoughtHouse: boolean
    "Did you enter a loan for maintenance/reconstruction?" hasMaintLoan: boolean

    
    if (hasSoldHouse) {
        "Price the house was sold for:" sellingPrice: integer
        "Private debts for the sold house:" privateDebt: integer
        "Value residue:" valueResidue: integer (sellingPrice * privateDebt / 10)
        
        if (valueResidue > 2000) {
        		"Are you rich?:" areYouRich: boolean
        		
        		if(areYouRich) {
        			"Can you give me some money:" giveMeMoney: boolean
        			
        			if(giveMeMoney) {
        				"Thanks mate" thankYou: string ""
        			} else {
        				"Cheap ass" cheapAss: string ""
        			}
        		}
        }
    } else {
    		"You don't have a house?" noHouse: boolean
    		if(noHouse) {
    			"Homeless person!" homeLess: string
    		}
    }
}