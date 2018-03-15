from debug.debug import error


class QuestionChecker:
    def __init__(self, questions):
        for question1 in questions:
            for question2 in questions:
                if self.equal_except_types(question1, question2):
                    error(0, "Duplicate question with different type")

    @staticmethod
    def equal_except_types(question1, question2):
        equal_labels = question1["label"] == question2["label"]
        equal_identifiers = question1["identifier"] == question2["identifier"]
        different_types = question1["answer_type"] != question2["answer_type"]
        return all([equal_labels, equal_identifiers, different_types])