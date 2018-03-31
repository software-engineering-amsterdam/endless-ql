from antlr4.error.ErrorListener import ErrorListener


class MyErrorListener(ErrorListener):
    """ Uses Errorlistener from antlr4 """

    def __init__(self):
        super(MyErrorListener, self).__init__()
        self.errors = []

    def syntaxError(self, recognizer, offending_symbol, line, column, msg, e):
        self.errors.append(f"SyntaxError: line { line } : { column } - { msg } - { offending_symbol }")

    def reportAmbiguity(self, recognizer, dfa, start_index, stop_index, exact, ambig_alts, configs):
        pass
        # self.errors.append(f"Antlr4 grammar ErrorListener: Ambiguity: "
        #                    f"{ recognizer } - { start_index } - { stop_index } - { exact }")

    def reportAttemptingFullContext(self, recognizer, dfa, start_index, stop_index, conflicting_alts, configs):
        pass
        # self.errors.append(f"Antlr4 grammar ErrorListener: FullContextAttempt: "
        #                    f"{ recognizer } - { start_index } - { stop_index } - { conflicting_alts }")

    def reportContextSensitivity(self, recognizer, dfa, start_index, stop_index, prediction, configs):
        self.errors.append(f"ContextSensitivity: { recognizer } - { start_index } - { stop_index } - { prediction }")

    def get_errors(self):
        """ Needed due to parent being protected """
        return [k for k in self.errors if 'EOF' not in k]
