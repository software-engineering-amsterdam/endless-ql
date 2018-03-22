form taxFormExample {
    "What was the selling price?"
        sellingPrice: money
    "Private debts for the sold house:"
        privateDebt: money
    "Value residue:"
        valueResidue: money = 
            (sellingPrice - privateDebt * 2)
}