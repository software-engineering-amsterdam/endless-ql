form taxOfficeExample {

  "What is your name"
    name: string

  "Do you have euros"
    euros: money_euro

  "Did you have dollars?"
    dollars: money_dollar

  "Do you want more money?"
    more: boolean

  if(more) {
      "Why do you want money"
        why: string

      "When do you want to get it?"
        when: date

    "pick a random number?"
      random: integer

    "pick a larger number?"
      larger: decimal

     if ( random < larger ) {
        "Did you know that your second number is larger?"
          know: boolean

        "multiplyed it is"
          mult: decimal = random * larger

      "oposide devided it is"
        div: decimal = larger / random

      "how nice am I?"
        nice: decimal

      "how super nice am I?"
        superNice: decimal

      "Are these questions good?"
        what: boolean

      "Are you really sure?"
              real: boolean

     }
  }

}
