form taxOfficeExample {

    if(hasSoldHouse) {
            "Did you sell a house in 2010?"
                hasSoldHouse: boolean
    } else {
            "Did you sell a house in 2011?"
                hasSoldHouse: decimal
    }
}