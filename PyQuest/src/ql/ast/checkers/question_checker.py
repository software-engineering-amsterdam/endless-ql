class QuestionChecker:
    def __init__(self, questions, debug):
        self.__debug = debug
        self.__check_questions(questions)

    def __check_questions(self, questions):
        for question1 in questions:
            for question2 in questions:
                if question1 != question2:

                    equal_labels = question1['label'] == question2['label']
                    equal_identifiers = question1['identifier'] == question2['identifier']
                    different_types = question1['answer_type'] != question2['answer_type']

                    if equal_identifiers:
                        self.__debug.error([question1['position'].line, question2['position'].line],
                                           'Duplicate question identifiers found')
                    if equal_labels:
                        self.__debug.warning([question1['position'].line, question2['position'].line],
                                             'Duplicate question labels found')
                        if different_types:
                            self.__debug.error([question1['position'].line, question2['position'].line],
                                               'Duplicate questions with different types found')
                            break
