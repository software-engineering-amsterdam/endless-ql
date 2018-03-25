from antlr4.error.ErrorListener import ErrorListener


class MyErrorListener(ErrorListener):
    """ Uses Errorlistener from antlr4 """

    def __init__(self):
        super(MyErrorListener, self).__init__()

    def syntaxError(self, recognizer, offending_symbol, line, column, msg, e):
        raise Exception(f"SyntaxError: { recognizer } - { line } - { msg } - { e }")

    def reportAmbiguity(self, recognizer, dfa, start_index, stop_index, exact, ambig_alts, configs):
        print(f"Antlr4 grammar ErrorListener: Ambiguity: "
              f"{ recognizer } - { start_index } - { stop_index } - { exact }")

    def reportAttemptingFullContext(self, recognizer, dfa, start_index, stop_index, conflicting_alts, configs):
        print(f"Antlr4 grammar ErrorListener: FullContextAttempt: "
              f"{ recognizer } - { start_index } - { stop_index } - { conflicting_alts }")

    def reportContextSensitivity(self, recognizer, dfa, start_index, stop_index, prediction, configs):
        raise Exception(f"ContextSensitivity: { recognizer } - { start_index } - { stop_index } - { prediction }")