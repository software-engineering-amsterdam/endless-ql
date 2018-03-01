import sys
from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor

from pyql.ast.static_analysis.type_check import TypeChecker

from pyql.ast import expression_visitor

from pyql.ast.expression.expressions import *
from pyql.ast.expression.literals import *


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

    cn = Multiplication("", IntegerLiteral("", 1), IntegerLiteral("", 2))
    e = expression_visitor.ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)

    cn = Addition("", StringLiteral("", "1"), StringLiteral("", "2"))
    e = expression_visitor.ExpressionEvaluator()
    ee = cn.accept(e)
    print(ee)

    vv = TypeChecker()
    c.accept(vv)
    print(c)


if __name__ == '__main__':
    main(sys.argv)
