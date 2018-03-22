form taxOfficeExample {
   if(true) {
      "Did you sell a house in 2010?"
        hasSoldHouse: boolean = false
        if(true) {
          "Did you sell a house in 2010?"
            hasSoldHouse2: boolean
        } else {
          "Did you sell a house in 2010?"
            hasSoldHouse: boolean
        }
    }
}