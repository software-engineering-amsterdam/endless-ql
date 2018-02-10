from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor

class Visitor(QLGrammarVisitor):
    def __init__(self):
        self.program = {}

    def visitForm(self, ctx):
        print ("VISIT FORM");