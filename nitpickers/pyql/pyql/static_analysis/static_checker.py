from pyql.static_analysis.duplication import CheckDuplicatedQuestions
from pyql.static_analysis.dependency import VariableDependenciesChecker
from pyql.static_analysis.division_by_zero import CheckDivisionByZero
from util.message_handler import MessageHandler
from pyql.static_analysis.dependency import CyclicDependenciesChecker


class StaticChecker:

    def run(self, tree):
        print("****** Check Duplicated Questions *****")
        cdq = CheckDuplicatedQuestions()
        cdq.check(tree)

        print("****** Variable Dependencies Checker ******")
        vdc = VariableDependenciesChecker()
        vdc.check(tree)

        print("****** Check Division By Zero ******")
        cdbz = CheckDivisionByZero()
        cdbz.check(tree)

        print("****** CyclicDependenciesChecker ******")
        cdc = CyclicDependenciesChecker()
        cdc.check(tree)

    def messages(self):
        return MessageHandler().messages
