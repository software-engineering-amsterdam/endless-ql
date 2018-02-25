form taxFormExample { 
    "What is your age?"
        age: integer
    "What is your date of Birth?"
        birthDate: date
    "Did you sell a house in 2010?"
        hasSoldHouse: boolean
    "Did you buy a house in 2010?"
        hasBoughtHouse: boolean
    "Did you enter a loan?"
        hasMaintLoan: boolean
        
    if (hasSoldHouse) {
        "What was the selling price?"
            sellingPrice: decimal
        "Private debts for the sold house:"
            privateDebt: money
        "Value residue:"
            valueResidue: money = 
                (sellingPrice - privateDebt * 2)
	    if (undefined) {
	        "Are you a minor?"
	            old: boolean
	    }	
    }
    
    if(old) {
    		"Undefined" undefined: boolean
    		"Did you sell a house in 2010?" hasSoldHouse: boolean
	}
    
    "Index" index: integer = (index+1)
    
    if(undefined1 != hasSoldHouse) {}

    "Undefined 1" undefined1: boolean
    
    "Did you steal in 2010?"
        hasStolen: boolean

    if (hasStolen) {
        "How much did you steal?"
            stealingAmount: money
        "Did you hurt anyone in the process?"
            hurtAnyone: boolean
        "Did you get caught?"
            gotCaught: boolean
        "Did you get caught?"
            gotCaught: boolean
    } else {
        "Did you get caught?"
            gotCaught: string = ("hello" == "world")
    }

	if(!2) {}
}