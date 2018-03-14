"""
    Generate the questions from the varDict and Ast
"""

import pprint
from GUI import Gui
class Question_Generator:

    def __init__(self, varDict, ast):
        self.varDict = varDict
        self.ast = ast
        self.gui = Gui()
        self.create_form()
        
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



def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)