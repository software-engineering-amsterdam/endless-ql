class SymbolTable:

    def __init__(self):
        self._dictionary = {}

    @property
    def dictionary(self):
        return self._dictionary

    def create(self, key, value):
        if key in self._dictionary:
            raise KeyError("Key {0} already exists".format(key))
        print("Creating {0}".format(key))
        self._dictionary[key] = value

    def update(self, key, value):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        self._dictionary[key] = value

    def update_or_create(self, key, value):
        self._dictionary[key] = value

    def get(self, key):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        return self._dictionary[key]

    def remove(self, key):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        print("Removing {0}".format(key))
        del self._dictionary[key]

    def __str__(self):
        res = "Keys: "
        for key in self._dictionary.keys():
            res += key + " "
        return res
