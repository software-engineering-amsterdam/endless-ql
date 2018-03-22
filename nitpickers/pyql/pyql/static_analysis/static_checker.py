from pyql.static_analysis.duplication import CheckDuplicatedQuestions
from pyql.static_analysis.dependency import VariableDependenciesChecker
from pyql.static_analysis.division_by_zero import CheckDivisionByZero
from pyql.util.message_handler import MessageHandler


class StaticChecker:

    def run(self, tree):
        self._messages = []

        print("****** Check Duplicated Questions *****")
        cdq = CheckDuplicatedQuestions()
        self._messages.extend(cdq.check(tree))

        print("****** Variable Dependencies Checker ******")
        vdc = VariableDependenciesChecker()
        vdc.check(tree)

        print("****** Check Division By Zero ******")
        cdbz = CheckDivisionByZero()
        cdbz.check(tree)

        print(self.messages())

    def messages(self):
        return MessageHandler().messages
