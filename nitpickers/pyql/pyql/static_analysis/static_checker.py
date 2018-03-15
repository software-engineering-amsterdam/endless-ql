from pyql.static_analysis.duplication import CheckDuplicatedQuestions
from pyql.static_analysis.dependency import VariableDependenciesChecker


class StaticChecker:

    def run(self, tree):
        print("****** Check Duplicated Questions *****")
        cdq = CheckDuplicatedQuestions()
        print(cdq.check(tree))
        print("****** Variable Dependencies Checker ******")
        vdc = VariableDependenciesChecker(tree)
        print(vdc.check())
    
        # stb = SymbolTableBuilder()
        # st = stb.build_from_tree(c)
        #
        # print(st)
        #
        # vv = ASTVisitor(stb.symbol_table)
        # c.accept(vv)
