class QuestionChecker:
    def __init__(self, questions):
        self.__errors = []
        self.__check_questions(questions)

    @property
    def errors(self):
        return self.__errors

    def __check_questions(self, questions):
        for question1 in questions:
            for question2 in questions:
                if question1 != question2:

                    equal_labels = question1['label'] == question2['label']
                    equal_identifiers = question1['identifier'] == question2['identifier']
                    different_types = question1['answer_type'] != question2['answer_type']

                    if equal_identifiers:
                        self.__errors.append('Duplicate question identifiers found on lines {} and {}'
                                             .format(question1['position'].line, question2['position'].line))
                    if equal_labels:
                        self.__errors.append('Warning: Duplicate question labels found on lines {} and {}'
                                             .format(question1['position'].line, question2['position'].line))
                        if different_types:
                            self.__errors.append('Duplicate questions with different types found on lines {} and {}'
                                                 .format(question1['position'].line, question2['position'].line))
                            break


