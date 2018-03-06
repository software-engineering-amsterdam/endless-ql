form Box1HouseOwning {
	"Did you sell a house in 2010?" hasSoldHouse: integer
	"What was the buying price?" houseBuyingPrice: integer = 10000
	"What was the selling price?" houseSellingPrice: integer = 10000 - hasSoldHouse
}