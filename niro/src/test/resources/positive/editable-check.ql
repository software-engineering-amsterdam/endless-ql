form EditOrNotToEdit {
    "String variable" stringVariable: string
    "Boolean variable" booleanVariable: boolean
    "Integer variable" integerVariable: integer
    "Decimal variable" decimalVariable: decimal
    "Money variable" moneyVariable: money
    "Date variable" dateVariable: date
    if (booleanVariable) {
        "String constant" stringConstant: string = ("NiRo")
        "Boolean constant" booleanConstant: boolean = (false)
        "Integer constant" integerConstant: integer = (42)
        "Decimal constant" decimalConstant: decimal = (42.4)
        "Money constant" moneyConstant: money = (42.42)
        "Date constant" dateConstant: date //= ("01-01-1970")
    } else {
        "String expression" stringExpression: string // = (stringConstant + stringVariable)
        "Boolean expression" booleanExpression: boolean = (!booleanConstant)
        "Integer expression" integerExpression: integer = (integerConstant + 1 + integerVariable)
        "Decimal expression" decimalExpression: decimal = (decimalConstant + 1.0 + decimalVariable)
        "Money expression" moneyExpression: money = (moneyConstant + 1.00)
        "Date expression" dateExpression: date = (dateConstant)
    }
}