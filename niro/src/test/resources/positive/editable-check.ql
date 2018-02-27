form EditOrNotToEdit {
    "Boolean variable" booleanVariable: boolean
    "Integer variable" integerVariable: integer
    "Decimal variable" decimalVariable: decimal
    "Money variable" moneyVariable: money
    "Date variable" dateVariable: date

    "Boolean constant" booleanConstant: boolean = (false)
    "Integer constant" integerConstant: integer = (42)
    "Decimal constant" decimalConstant: decimal = (42.4)
    "Money constant" moneyConstant: money = (42.42)
    "Date constant" dateConstant: date //= ("01-01-1970")

    "Boolean expression" booleanExpression: boolean = (!booleanConstant)
    "Integer expression" integerExpression: integer = (integerConstant + 1)
    "Decimal expression" decimalExpression: decimal = (decimalConstant + 1.0)
    "Money expression" moneyExpression: money = (moneyConstant + 1.00)
    "Date expression" dateExpression: date = (dateConstant)
}