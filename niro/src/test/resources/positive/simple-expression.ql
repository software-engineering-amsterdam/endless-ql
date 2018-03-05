form Box1HouseOwning {
	"Did you sell a house in 2010?" hasSoldHouse: integer
	"What was the selling price?" houseSellingPrice: integer = ( 10000 - hasSoldHouse)
}