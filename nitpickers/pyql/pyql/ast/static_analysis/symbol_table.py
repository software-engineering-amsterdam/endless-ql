class SymbolTable:

    def __init__(self):
        self._dictionary = {}

    @property
    def dictionary(self):
        return self._dictionary

    def create(self, key, value):
        if key in self._dictionary:
            raise Exception("Name already exists")
        self._dictionary[key] = value

    def update(self, key, value):
        if key not in self._dictionary:
            raise Exception("Invalid key")
        self._dictionary[key] = value

    def get(self, key):
        if key not in self._dictionary:
            raise Exception("Key not in dictionary")
        return self._dictionary[key]

    def remove(self, key):
        if key not in self._dictionary:
            raise Exception("Key not in dictionary")
        del self._dictionary[key]


if __name__ == "__main__":
    s = SymbolTable()
    s.create(3, "marian")
    print(s.dictionary)
    print(s.get(3))
    s.update(3, "marian2")
    print(s.get(3))
    s.remove(4)
    print(s.get(3))
