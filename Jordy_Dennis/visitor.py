from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor

class Visitor(QLGrammarVisitor):
	def __init__(self):
		self.program = {}

	def visitForm(self, ctx):
		name = ctx.ID().getText()
		print(name)
		return self.visitChildren(ctx)

	def visitGroupquestions(self, ctx):
		print("groupQ")
		return self.visitChildren(ctx)

	def visitQuestion(self, ctx):
		print("question")
		return self.visitChildren(ctx)