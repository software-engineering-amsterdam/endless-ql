form taxOfficeExample {

  "What is your name?"
    name: string

  "How many euros do you have?"
    euros: money_euro

  "How many dollars did you have?"
    dollars: money_dollar

  "Do you want more money?"
    more: boolean

  if(more) {
      "Why do you want money?"
        why: string

      "When do you want to get it?"
        when: date

    "Pick a random number"
      random: integer

    "Pick a larger number"
      larger: decimal

     if ( random < larger ) {
        "Did you know that your second number is larger?"
          know: boolean

        "Multiplyed it is:"
          mult: decimal = random * larger

      "Opposite devided it is:"
        div: decimal = larger / random

      "How nice am I?"
        nice: decimal

      "How super nice am I?"
        superNice: decimal

      "Are these questions good?"
        what: boolean

      "Are you really sure?"
              real: boolean
     }
  }
}
