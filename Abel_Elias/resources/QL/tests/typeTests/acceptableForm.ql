form typesForm {
  first: "first" integer
  second: "second" integer

  third: "third" money
  fourth: "fourth" money

  fifth: "fifth" string

  sixth: "sixth" boolean
  seventh: "seventh" boolean

  addition1: "addition" integer (first + second)
  addition2: "addition" money (third + fourth)

  subtraction1: "addition" money (first - second)
  subtraction2: "addition" money (third - fourth)

  greaterThan1: "greaterThan" boolean (first > second)
  greaterThan2: "greaterThan" boolean (third > fourth)
}