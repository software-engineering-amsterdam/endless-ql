from debug.debug import Debug


class QuestionChecker:
    def __init__(self, questions):
        self.__check_questions(questions)

    @staticmethod
    def __check_questions(questions):
        for question1 in questions:
            for question2 in questions:
                if question1 != question2:

                    equal_labels = question1["label"] == question2["label"]
                    equal_identifiers = question1["identifier"] == question2["identifier"]
                    different_types = question1["answer_type"] != question2["answer_type"]

                    if equal_identifiers:
                        Debug().error([question1["position"].line, question2["position"].line],
                                      "Duplicate question identifiers found")
                    if equal_labels:
                        Debug().warning([question1["position"].line, question2["position"].line],
                                        "Duplicate question labels found")
                        if different_types:
                            Debug().error([question1["position"].line, question2["position"].line],
                                          "Duplicate questions with different types found")
                            break
