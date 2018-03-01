import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor

from pyql.static_analysis.type_check import TypeChecker
from pyql.static_analysis.expression_evaluator import ExpressionEvaluator

from pyql.static_analysis.symbol_table import *

from pyql.ast.expression.expressions import *
from pyql.ast.expression.literals import *

import decimal


def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    tree = parser.form()
    print(tree)
    b = type(tree)
    visitor = ParseTreeVisitor()
    c = tree.accept(visitor)
    print(c)

    ss = SymbolTableBuilder().build(c)

    cn = Multiplication("", IntegerLiteral("", 3), IntegerLiteral("", 7))
    e = ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)

    cn = Division("", DecimalLiteral("", 14.0), IntegerLiteral("", 4))
    e = ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)

    cn = GreaterThan("", IntegerLiteral("", 3), IntegerLiteral("", 7))
    e = ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)

    cn = GreaterThan("", DecimalLiteral("", decimal.Decimal('11.9999')), IntegerLiteral("", 12))
    e = ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)
    #
    # cn = Addition("", StringLiteral("", "1"), StringLiteral("", "2"))
    # e = ExpressionEvaluator()
    # ee = cn.accept(e)
    # print(ee)

    # cn = Multiplication("", IntegerLiteral("", "1"), StringLiteral("", "2"))
    # e = ExpressionEvaluator()
    # ee = cn.accept(e)
    # print(ee)

    vv = TypeChecker()
    c.accept(vv)
    print(c)


if __name__ == '__main__':
    main(sys.argv)
