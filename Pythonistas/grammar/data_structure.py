import antlr4
from parser_generator.grammar.QLLexer import QLLexer
from parser_generator.grammar.QLParser import QLParser
from parser_generator.grammar.QLSLexer import QLSLexer
from parser_generator.grammar.QLSParser import QLSParser


class ParserCarrier:
    def __init__(self):
        self.ql_grammar_text = None
        self.qls_grammar_text = None
        self.file_path = None
        self.error = None
        self.ql_tree = None
        self.qls_tree = None
        self.lexer = None
        self.parser = None

    def set_ql_grammar_text(self, ql_text):
        self.ql_grammar_text = ql_text

    def set_qls_grammar_text(self, qls_text):
        self.qls_grammar_text = qls_text

    def run_antlr_ql(self):
        ql_input = antlr4.InputStream(self.ql_grammar_text)
        self.lexer = QLLexer(ql_input)
        stream = antlr4.CommonTokenStream(self.lexer)
        self.parser = QLParser(stream)
        self.ql_tree = self.parser.form()

    def run_antlr_qls(self):
        qls_input = antlr4.InputStream(self.qls_grammar_text)
        self.lexer = QLSLexer(qls_input)
        stream = antlr4.CommonTokenStream(self.lexer)
        self.parser = QLSParser(stream)
        self.qls_tree = self.parser.stylesheet()
