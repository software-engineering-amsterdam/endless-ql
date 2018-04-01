form typesForm {
  first: "first" integer
  second: "second" money
  third: "third" string
  fourth: "fourth" boolean

  addition1: "addition" integer (first + second)
  addition2: "addition" integer (first + third)
  addition3: "addition" integer (first + fourth)

  subtraction1: "addition" money (second - third)
  subtraction2: "addition" money (second - fourth)

  greaterThan1: "greaterThan" boolean (third > fourth)
}