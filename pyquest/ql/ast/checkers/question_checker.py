class QuestionChecker:
    def __init__(self, questions):
        self.__errors = []
        self.__warnings = []
        self.__check_questions(questions)

    @property
    def errors(self):
        return self.__errors

    @property
    def warnings(self):
        return self.__warnings

    def __check_questions(self, questions):
        for question1 in questions:
            for question2 in questions:
                if question1 != question2:
                    equal_labels = question1['label'] == question2['label']
                    equal_identifiers = question1['identifier'] == question2['identifier']
                    different_types = question1['answer_type'] != question2['answer_type']

                    if all([equal_labels, equal_identifiers, different_types]):
                        self.__errors.append('Duplicate questions with different types found on lines {} and {}'
                                             .format(question1['metadata'].line, question2['metadata'].line))

                    if equal_identifiers:
                        self.__errors.append('Duplicate question identifiers found on lines {} and {}'
                                             .format(question1['metadata'].line, question2['metadata'].line))

                    if equal_labels:
                        self.__warnings.append('Duplicate question labels found on lines {} and {}'
                                               .format(question1['metadata'].line, question2['metadata'].line))
