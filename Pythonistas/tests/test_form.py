"""
pytest: https://docs.pytest.org/en/latest/
"""
import unittest


def add_one(x):
    return x + 1


# todo: write tests
def test_form():
    # form testform {}
    # "Did you sell a house in 2010?" \n hasSoldHouse: boolean
    # more
    assert add_one(3) == 4


def test_parse():
    pass
