form Box1HouseOwning {
    "Did you sell a house in 2010?" hasSoldHouse:  boolean
    if (hasSoldHouse) {
        "Price the house was sold for:" sellingPrice: money = (6)
    }
}
