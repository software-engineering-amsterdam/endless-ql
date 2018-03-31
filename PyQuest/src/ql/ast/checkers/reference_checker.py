class ReferenceChecker:
    def __init__(self, table):
        self.__errors = []
        self.__check_invalid_references(table)

    @property
    def errors(self):
        return self.__errors

    def __check_invalid_references(self, table):
        known_types = [row['name'] for row in table['content'] if row['type']]
        unknown_types = [(row['name'], row['position']) for row in table['content'] if not (row['type'])]

        self.__identifier_seen(known_types, unknown_types)

        if table['children']:
            for child_scope in table['children']:
                (nested_known_types, nested_unknown_types) = self.__check_invalid_references(child_scope)
                self.__identifier_seen(nested_known_types + known_types, nested_unknown_types + unknown_types)

        return known_types, unknown_types

    def __identifier_seen(self, known_types, unknown_types):
        for (identifier, position) in unknown_types:
            if not (identifier in known_types):
                self.__errors.append('Identifier \"{}\" at position {} is unknown'.format(identifier, position))
