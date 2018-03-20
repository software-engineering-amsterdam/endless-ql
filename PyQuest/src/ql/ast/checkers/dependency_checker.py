class DependencyChecker:
    def __init__(self, combinations, debug):
        self.__debug = debug
        self.__check_dependencies(combinations)

    def __check_dependencies(self, combinations):
        for (identifier, identifier_children, identifier_position) in combinations:
            parents = [(reference, reference_position) for (reference, reference_children, reference_position)
                       in combinations if identifier in reference_children]
            for (parent, reference_position) in parents:
                if parent in identifier_children:
                    self.__debug.error([identifier_position.line, reference_position.line],
                                       "Cyclic dependency detected in question identifiers")
