"""
    Generate the questions from the varDict and Ast
"""

import pprint
from AST import *
import collections


class Question_Generator:

    def __init__(self, varDict, ast, form):
        self.varDict = varDict
        self.ast = ast
        self.questions = collections.OrderedDict()
        self.form = form

    def getVarDict(self):
        return self.varDict

    # Get a list of all the questions that need to be rendered (depending on the evaluation of the statements)
    def updateQuestions(self, initial = False):
        self.questions = collections.OrderedDict()
        self.get_questions(self.ast.form.block)
        tmp_dict = self.questions.copy()
        tmp_bool = False
        if self.form:
            # self.form.empty_frame()
            for varName in self.questions:
                label = self.questions[varName].getQuestion()
                type = self.varDict[varName]['node'].checkTypes()
                value = self.varDict[varName]['node'].evaluate()
                if(not checkQuestions(self.form.questions,label)):
                    if not initial:
                        for varName2 in tmp_dict:
                            for formQuestion in self.form.questions:
                                if formQuestion.varName == varName2:
                                    formQuestion.frame.destroy()
                                    self.form.questions.remove(formQuestion)

                    self.form.add_question(varName, label, type, value)
                    # pack to previous iteration
                del tmp_dict[varName]

            # remove if question if no longer valid
            for question in self.form.questions:
                if(question.varName not in self.questions):
                    question.frame.destroy()
                    self.form.questions.remove(question)

        return self.questions

    # Create the list of all the questions by recursively looping through the statements and adding them to te dictionairy
    def get_questions(self, block):
        for statement in block:
            if type(statement) == QuestionNode:
                self.questions[statement.getVarName()] = statement
            elif type(statement) == AssignmentNode:
                statement.evaluate(self.varDict)


            elif type(statement) == ConditionalNode:
                visited = False
                # check if block
                ifblock = statement.getIf();
                if_exp = ifblock.getExpression()

                if (if_exp.evaluate()):
                    self.get_questions(ifblock.block)
                    visited = True

                # check elif block
                if (not visited):
                    elifBlocks = statement.getElIf()
                    for elifBlock in elifBlocks:
                        elif_exp = elifBlock.getExpression()
                        if (elif_exp.evaluate()):
                            self.get_questions(elifBlock.block)
                            visited = True
                            break

                # check else block
                elseBlock = statement.getElse()
                if (elseBlock and not visited):
                    self.get_questions(elseBlock)

def checkQuestions(questions, label):
    for question in questions:
        if question.question_text  == label:
            return True
    return False

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)
