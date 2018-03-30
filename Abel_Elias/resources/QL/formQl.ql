form Box1HouseOwning {
     hasBoughtHouse: "Did you buy a house in 2010?" boolean
     if(hasBoughtHouse) {
        hasBoughtHouse2: "Did you buy a house in 2011?" boolean
     }
     hasSoldHouse: "Did you sell a house in 2010?" boolean
     if(hasSoldHouse) {
        hasSoldHouse2: "Did you sell a house in 2011?" boolean
     }
}