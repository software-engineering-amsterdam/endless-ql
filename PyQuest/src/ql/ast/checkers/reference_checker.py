from debug.debug import error


class ReferenceChecker:
    def __init__(self, table):
        self.check_invalid_references(table)

    def check_invalid_references(self, table):
        known_types = [row["name"] for row in table["content"] if row["type"]]
        unknown_types = [row["name"] for row in table["content"] if not (row["type"])]

        self.identifier_seen(known_types, unknown_types)

        if table["children"]:
            for child_scope in table["children"]:
                (nested_known_types, nested_unknown_types) = self.check_invalid_references(child_scope)
                self.identifier_seen(nested_known_types + known_types, nested_unknown_types + unknown_types)

        return known_types, unknown_types

    @staticmethod
    def identifier_seen(known_types, unknown_types):
        for identifier in unknown_types:
            if not (identifier in known_types):
                error(0, "Identifier \"{}\" is unknown".format(identifier))



