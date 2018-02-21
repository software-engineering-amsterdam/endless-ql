form Box1HouseOwning {
	hasSoldHouse: "Did you sell a house in 2010?" boolean
	houseSellingPrice: "What was the selling price?" integer = ( 10000 - hasSoldHouse)
}