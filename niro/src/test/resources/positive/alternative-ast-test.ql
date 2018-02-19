form NewASTForm {
    hasSoldHouse: "Did you sell your house this year?" boolean
    if (!hasSoldHouse) {
        priceOfHouse: "What is the price you sold your house for?"  money
    } else {
        willSellHouse: "Are you going to sell your house this year?" boolean
        houseSellingTax: "Tax when selling house" money = (1500.00)
    }
}
