// Tax form example provided by Vadim Zaytsev

form taxOfficeExample
{

  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: date
  if (hasSoldHouse)
  {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = (sellingPrice - privateDebot)
  } else {
    if(hasBoughtHouse) {
        "What was the selling price?2"
          sellingPrice: money
      } else {
        "What was the selling price?3"
          sellingPrice: money
      }
  }
}