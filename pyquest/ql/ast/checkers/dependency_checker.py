class DependencyChecker:
    def __init__(self, combinations):
        self.__errors = []
        self.__check_dependencies(combinations)

    @property
    def errors(self):
        return self.__errors

    def __check_dependencies(self, combinations):
        for (identifier, identifier_children, identifier_metadata) in combinations:
            children = self.__find_children(identifier, combinations)

            if identifier in children:
                self.__errors.append('Cyclic dependency detected in question identifiers on line(s) {}'
                                     .format(str(self.__find_lines(children, combinations))[1:-1]))

    def __find_children(self, identifier, combinations):
        reference, reference_children, _ = combinations[0]

        if len(combinations) == 1 and identifier == reference:
            return reference_children
        elif len(combinations) == 1:
            return []
        elif identifier == reference:
            all_children = []

            for new_identifier in reference_children:
                all_children += self.__find_children(new_identifier, combinations[1:])

            return all_children + reference_children

        return self.__find_children(identifier, combinations[1:])

    @staticmethod
    def __find_lines(children, combinations):
        lines = []

        for identifier, _, metadata in combinations:
            for child in children:
                if identifier == child:
                    lines.append(metadata.line)

        return lines
