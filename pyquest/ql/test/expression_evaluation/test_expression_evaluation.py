from json import loads

from ql.ast.extractors.extractor import extract_gui_model
from ql.ast.extractors.extractor import extract_identifier_types
from ql.ast.visitors.expression_evaluator import ExpressionEvaluator
from ql.ast.visitors.type_visitor import TypeVisitor
from ql.types.boolean import QLBoolean
from ql.types.date import QLDate
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.string import QLString
from ql.test.test import Test


class TestExpressionEvaluation(Test):
    def __init__(self, directory, lexer, parser):
        super(TestExpressionEvaluation, self).__init__('expression evaluation', directory)
        self.__lexer = lexer
        self.__parser = parser

    def test_file(self, file):
        ast = self.__parser.parse(file, self.__lexer.lexer)

        if not self.__parser.errors:
            TypeVisitor(extract_identifier_types(ast)).visit(ast)
            model = extract_gui_model(ast)
            result_type, result_value = file.split('\n')[0].split()[-2:]
            correct_result = None

            if result_type == 'QLBoolean' and result_value == 'True':
                correct_result = QLBoolean(True)
            elif result_type == 'QLBoolean':
                correct_result = QLBoolean()
            elif result_type == 'QLDate':
                day, month, year = loads(result_value)
                correct_result = QLDate(day, month, year)
            elif result_type == 'QLDecimal':
                correct_result = QLDecimal(result_value)
            elif result_type == 'QLInteger':
                correct_result = QLInteger(result_value)
            elif result_type == 'QLMoney':
                correct_result = QLMoney(result_value)
            elif result_type == 'QLString':
                correct_result = QLString(result_value)

            expression_evaluator = ExpressionEvaluator(model)
            expression_evaluator.visit(ast.block[0].answer)
            return bool(expression_evaluator.result == correct_result)
