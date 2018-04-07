form taxOfficeExample
{

  "What is your name?"
    name: string
  "What is your age?"
    age: integer
  "When is your birthday this year?"
    birthday: date
  "What is your monthly income?"
    income: money
  "What are your spendings?"
    spendings: money
  "You are saging" savings: money = income - spendings

  "Do you have any debts?"
    debts: boolean
  "Choose an integer"
    someNumber: integer
}