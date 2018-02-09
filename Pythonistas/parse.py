import ast
from astviewer.main import view


form_string = 'form taxOfficeExample {"Did you sell a house in 2010?" hasSoldHouse: boolean "Did you buy a house in 2010?" hasBoughtHouse: boolean'


def parse_string(string):
    tree = ast.parse(string)
    return compile(tree, filename="<ast>", mode="exec")