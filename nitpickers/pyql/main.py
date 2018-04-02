from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor
from pyql.static_analysis.static_checker import StaticChecker
from pyql.gui.gui import GUI


def main(argv):
    input = FileStream(argv[1])
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    parse_tree = parser.form()

    visitor = ParseTreeVisitor()
    ast = parse_tree.accept(visitor)

    static_checker = StaticChecker()
    static_checker.run(ast)

    GUI(ast)


if __name__ == '__main__':
    filenames = [
        "pyql/antlr/example.ql",
        "pyql/test/samples/example.ql",
        "pyql/test/samples/form1.ql",
        "pyql/test/samples/form2.ql",
        "pyql/test/samples/form3.ql",
        "pyql/test/samples/form4.ql"
    ]
    main(filenames)
