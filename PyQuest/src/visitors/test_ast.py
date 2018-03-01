from scanparse.qlyacc import QLParser
from scanparse.qllex import LexTokenizer
from visitors.duplication_visitor import DuplicationVisitor

data = """form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }
  "Did you sell a house in 2010?"
    isReachable: integer
  "Did you sell a house in 2010?"
    isReachable: boolean

}
"""

ql_parser = QLParser()
ql_lexer = LexTokenizer()

parse_tree = ql_parser.parser.parse(data, ql_lexer.lexer)

visitor = DuplicationVisitor()
visitor.visit(parse_tree)

