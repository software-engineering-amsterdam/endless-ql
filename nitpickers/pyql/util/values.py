from util import types
import decimal
from multimethods import multimethod


class Value:
    def __init__(self, type, value):
        self._value = value
        self._type = type

    def __add__(self, other):
        return Addition().evaluate(self, other)

    def __sub__(self, other):
        return Subtraction().evaluate(self, other)

    def __mul__(self, other):
        return Multiplication().evaluate(self, other)

    def __truediv__(self, other):
        return Division().evaluate(self, other)

    def __eq__(self, other):
        return Equality().evaluate(self, other)

    def __ne__(self, other):
        return NonEquality().evaluate(self, other)

    def __lt__(self, other):
        return LowerThan().evaluate(self, other)

    def __le__(self, other):
        return LowerEqual().evaluate(self, other)

    def __gt__(self, other):
        return GreaterThan().evaluate(self, other)

    def __ge__(self, other):
        return GreaterEqual().evaluate(self, other)

    def __invert__(self):
        return Not().evaluate()

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def __repr__(self):
        return str(self.value)

    __str__ = __repr__


class StringValue(Value):

    def __init__(self, value):
        super().__init__(types.String, value)


class IntegerValue(Value):

    def __init__(self, value):
        super().__init__(types.Integer, int(value))


class DecimalValue(Value):

    def __init__(self, value):
        super().__init__(types.Decimal, decimal.Decimal(value))


class BooleanValue(Value):

    def __init__(self, value):
        super().__init__(types.Boolean, value)


class MoneyValue(Value):

    def __init__(self, value):
        super().__init__(types.Money, round(decimal.Decimal(value), 2))

    def __repr__(self):
        return "$" + str(self.value)


class Addition:

    @multimethod([(StringValue, StringValue)])
    def evaluate(self, left, right):
        return StringValue(left.value + right.value)

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value + right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value + right.value)

    @multimethod([(MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value + right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise TypeError("Incompatible operand types for addition")


class Subtraction:

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value - right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value - right.value)

    @multimethod([(MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value - right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise TypeError("Incompatible operand types for addition")


class Multiplication:

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value * right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value * right.value)

    @multimethod(
        [(IntegerValue, MoneyValue), (DecimalValue, MoneyValue), (MoneyValue, IntegerValue), (MoneyValue, DecimalValue),
         (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value * right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise TypeError("Incompatible operand types for multiplication")


class Division:

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value / right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value / right.value)

    @multimethod(
        [(MoneyValue, IntegerValue), (MoneyValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value / right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise TypeError("Incompatible operand types for multiplication")


class Equality:
    @multimethod([(IntegerValue, IntegerValue), (DecimalValue, DecimalValue), (MoneyValue, MoneyValue),
                  (StringValue, StringValue), (BooleanValue, BooleanValue), (IntegerValue, DecimalValue),
                  (DecimalValue, IntegerValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value == right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)


class NonEquality:
    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return not Equality().evaluate(left, right)


class LowerThan:
    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value < right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise TypeError("Operation not possible between instances of ", left.type, ",", right.type)


class LowerEqual:
    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value <= right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)


class GreaterThan:
    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value > right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)


class GreaterEqual:
    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value >= right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)


class Not:
    @multimethod(BooleanValue)
    def evaluate(self, expression):
        return BooleanValue(not expression.value)


class Invert:
    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, expression):
        return Subtraction().evaluate(IntegerValue(0), expression.value)
