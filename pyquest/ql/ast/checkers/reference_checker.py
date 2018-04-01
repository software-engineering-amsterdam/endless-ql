class ReferenceChecker:
    def __init__(self, table):
        self.__errors = []
        self.__check_references(table, [], [])

    @property
    def errors(self):
        return self.__errors

    def __check_references(self, table, known_types, unknown_types):
        (local_known_types, local_unknown_types) = self.__split_known_unknown_types(table)
        known_types += local_known_types
        unknown_types += local_unknown_types

        self.__identifier_seen(known_types, unknown_types)

        if table['children']:
            for child_scope in table['children']:
                self.__check_references(child_scope, known_types, unknown_types)

    @staticmethod
    def __split_known_unknown_types(table):
        known_types = [row['name'] for row in table['content'] if row['type']]
        unknown_types = [(row['name'], row['line']) for row in table['content'] if not (row['type'])]
        return known_types, unknown_types

    def __identifier_seen(self, known_types, unknown_types):
        for (identifier, line) in unknown_types:
            if not (identifier in known_types):
                self.__errors.append('Identifier \"{}\" at line {} is unknown'.format(identifier, line))
