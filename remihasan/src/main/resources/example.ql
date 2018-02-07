form taxOfficeExample {
  someYesOrNo: "Yes or no?" boolean
  someMoneyVarX: "What price is X?" money
  someMoneyVarY: "What price is Y" money
  someMoneyVarZ: "What price is Z" money = (1 + 2)
  someMoneyVarA: "What price is A" money = (1 + 2 + 3)
  if(someMoneyVarA) {
  	someMoneyVarB: "Yes or no?" boolean = (someMoneyVarX < someMoneyVarY)
  }
}