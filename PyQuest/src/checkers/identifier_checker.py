from debug.debug import warning


class IdentifierChecker:
    def __init__(self, identifiers):
        for identifier in identifiers:
            if identifiers.count(identifier) > 1:
                warning(0, "Duplicate identifier detected")