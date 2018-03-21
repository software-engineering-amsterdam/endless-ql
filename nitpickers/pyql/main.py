from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor
from pyql.static_analysis.static_checker import StaticChecker
from pyql.static_analysis.symbol_table import SymbolTable
from pyql.gui.gui_visitor import GUIVisitor
from pyql.util.message_handler import MessageHandler


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
    messages = MessageHandler().messages
    print(messages)

    symbol_table = SymbolTable()

    GUIVisitor(ast, symbol_table, messages)


if __name__ == '__main__':
    filenames = [
        "pyql/test/samples/example.ql",
        "pyql/test/samples/form1.ql",
        "pyql/test/samples/form2.ql",
        "pyql/test/samples/form3.ql",
        "pyql/test/samples/form4.ql"
    ]
    main(filenames)
