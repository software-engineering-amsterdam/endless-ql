from commons.logging import *


class Result:
    """
    Result of the parser, consist out of value(part of AST) and position(index of the next token in stream)
    """
    def __init__(self, value, pos):
        self.value = value
        self.pos = pos

    def __repr__(self):
        return 'Result({}, {})'.format(self.value, self.pos)


class Parser:
    """
    Takes stream of tokens, provides functionality with operators
    """
    def __add__(self, other):
        return Concat(self, other)

    def __mul__(self, other):
        return Exp(self, other)

    def __or__(self, other):
        return Alternate(self, other)

    def __xor__(self, function):
        return Process(self, function)


class Phrase(Parser):
    """
    Ensured every token gets iterated
    """
    def __init__(self, parser):
        self.parser = parser

    def __call__(self, tokens, pos):
        result = self.parser(tokens, pos)
        if result and result.pos == len(tokens):
            return result
        else:
            return None


class Tag(Parser):
    """
    Call does the actual parsing
    """
    def __init__(self, tag):
        self.tag = tag

    def __call__(self, tokens, pos):
        if pos < len(tokens) and tokens[pos][1] is self.tag:
            return Result(tokens[pos][0], pos + 1)
        else:
            return None


class Reserved(Parser):
    """
    Used to parse reserved words and operators
    """
    def __init__(self, value, tag):
        self.value = value
        self.tag = tag

    def __call__(self, tokens, pos):
        if pos < len(tokens) and tokens[pos][0] == self.value and tokens[pos][1] is self.tag:
            return Result(tokens[pos][0], pos + 1)
        else:
            return None


class Concat(Parser):
    """
    For sequence of tokens
    """
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def __call__(self, tokens, pos):
        left_result = self.left(tokens, pos)
        if left_result is not None:
            logger.debug('Result Found: {}'.format(left_result))
        if left_result:
            right_result = self.right(tokens, left_result.pos)
            if right_result:
                combined_value = (left_result.value, right_result.value)
                return Result(combined_value, right_result.pos)
        return None


class Exp(Parser):
    def __init__(self, parser, separator):
        self.parser = parser
        self.separator = separator

    def __call__(self, tokens, pos):
        result = self.parser(tokens, pos)

        def process_next(parsed):
            (sepfunc, right) = parsed
            return sepfunc(result.value, right)
        next_parser = self.separator + self.parser ^ process_next

        next_result = result
        while next_result:
            next_result = next_parser(tokens, result.pos)
            if next_result:
                result = next_result
        return result


class Alternate(Parser):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def __call__(self, tokens, pos):
        left_result = self.left(tokens, pos)
        if left_result:
            return left_result
        else:
            right_result = self.right(tokens, pos)
            return right_result


class Opt(Parser):
    """
    Optional parsing (if/else)
    """
    def __init__(self, parser):
        self.parser = parser

    def __call__(self, tokens, pos):
        result = self.parser(tokens, pos)
        if result:
            return result
        else:
            return Result(None, pos)


class Rep(Parser):
    """
    Applies parser repeatedly
    """
    def __init__(self, parser):
        self.parser = parser

    def __call__(self, tokens, pos):
        results = []
        result = self.parser(tokens, pos)
        while result:
            results.append(result.value)
            pos = result.pos
            result = self.parser(tokens, pos)
        return Result(results, pos)


class Process(Parser):
    """
    Processes statement if corresponding with pattern matching
    """
    def __init__(self, parser, function):
        self.parser = parser
        self.function = function

    def __call__(self, tokens, pos):
        result = self.parser(tokens, pos)
        if result:
            result.value = self.function(result.value)
            return result


class Lazy(Parser):
    """
    Lazy eval for parser
    """
    def __init__(self, parser_func):
        self.parser = None
        self.parser_func = parser_func

    def __call__(self, tokens, pos):
        if not self.parser:
            self.parser = self.parser_func()
        return self.parser(tokens, pos)
