Software Construction
================================

Jorick van Rhenen   (jvrhenen)   11353341
Mihai Onofei        






Grammar:


parser: form

form: "form" identifier "{" questionnaire* "}"

questionnaire: question | structure

question: identifier '"' .? '"' type expression?
type: boolean | money


structure: conditionalstructure | groupingstructure
conditionalstructure:
groupingstructure:



identifier: [a-zA-Z_]

boolean: "boolean"
money: "money"
