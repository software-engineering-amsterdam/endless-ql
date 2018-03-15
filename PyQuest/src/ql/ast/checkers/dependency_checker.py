from debug.debug import error


class DependencyChecker:
    def __init__(self, combinations):
        self.__check_dependencies(combinations)

    @staticmethod
    def __check_dependencies(combinations):
        for (identifier, identifier_children) in combinations:
            parents = [reference for (reference, reference_children) in combinations
                       if identifier in reference_children]
            for parent in parents:
                if parent in identifier_children:
                    error(0, "Cyclic dependency detected")
