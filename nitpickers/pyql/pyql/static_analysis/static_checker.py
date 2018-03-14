from pyql.static_analysis.duplication import CheckDuplicatedQuestions


class StaticChecker:

    def run(self, tree):
        cdq = CheckDuplicatedQuestions()
        print(cdq.check(tree))
        # stb = SymbolTableBuilder()
        # st = stb.build_from_tree(c)
        #
        # print(st)
        #
        # vv = ASTVisitor(stb.symbol_table)
        # c.accept(vv)
