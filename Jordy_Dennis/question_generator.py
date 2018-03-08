"""
    Generate the questions from the varDict and Ast
"""

import pprint
from GUI import Gui
from AST import *
class Question_Generator:

    def __init__(self, varDict, ast):
        self.varDict = varDict
        self.ast = ast
        self.gui = Gui()
        self.create_form()
        self.questions = {}

    def create_form(self):
        # questions, qtypes = self.prepare_questions()
        self.prepare_questions()
        # self.gui.create_form(header="test", questions=questions, qtypes=qtypes)
        # self.gui.execute()

    # Add only the questions that need to be generated
    def prepare_questions(self):
        questions = []
        qtypes = []
        # for var in self.varDict:
        #     cur_node = self.varDict[var]['assign']
        #     if cur_node.getNodeType() == "Question":
        #         questions.append(cur_node.getQuestion())
        #         qtypes.append(self.varDict[var]["type"])
        # return questions, qtypes

    def getQuestionsFromAst(self):
        for form in self.ast.forms:
            self.get_questions(form.block)

    def get_questions(self, block):
        for statement in block:
            if type(statement) == QuestionNode:
                print("QUESTION")
                self.questions[statement.getVarName()] = statement
            elif type(statement) == AssignmentNode:
                statement.evaluate(self.varDict)


            elif type(statement) == ConditionalNode:
                print("CONDITIONAL")
                visited = False
                # check if block
                ifblock = statement.getIf();
                if_exp = ifblock.getExpression()
                if(if_exp.evaluate()):
                    self.get_questions(ifblock.block)
                    visited = True

                # check elif block
                if(not visited):
                    elifBlocks = statement.getElIf()
                    for elifBlock in elifBlocks:
                        elif_exp = elifBlock.getExpression()
                        if(elif_exp.evaluate()):
                            self.get_questions(elifBlock.block)
                            visited = True
                            break

                # check else block
                elseBlock = statement.getElse()
                if(elseBlock and not visited):
                    self.get_questions(elseBlock)

        # for statement in ast.statements():
        #     if statement is question:
        #         # voeg toe aan ordered dict
        #         # key = variable name, value = QuestionNode
        #     if statement is assignment:
        #         # voer uit en zet in self.vardict ( afhankelijk van vardict)
        #     if statement is conditional:
        #         #check if condition expression
        #             # is True, get_questions(children)
        #             # if False, check elif and else (opt) and do the same
        # # this generates a ordered dict (variable name: QuestionNode). This dict is used to look up nodes when
        # # looking at the QLS to generate question.



def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)