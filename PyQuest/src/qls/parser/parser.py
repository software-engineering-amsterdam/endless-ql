from ply.yacc import yacc
from qls.parser.lexer import QLSLexer


class QLSParser:
    def __init__(self):
        self.tokens = QLSLexer.tokens
        self.parser = yacc(module=self)

    def parse(self, data, lexer):
        return self.parser.parse(data, lexer)

    # Statements
    @staticmethod
    def p_stylesheet(p):
        """stylesheet : STYLESHEET IDENTIFIER LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = {p[2]: p[4]}

    @staticmethod
    def p_statements(p):
        """statements   : statement statements
                        | statement"""
        if len(p) == 3:
            p[0] = [p[1]] + p[2]
        elif len(p) == 2:
            p[0] = [p[1]]

    @staticmethod
    def p_statement(p):
        """statement    : page
                        | section
                        | question
                        | default
                        | stylesheet"""
        p[0] = p[1]

    @staticmethod
    def p_page(p):
        """page : PAGE IDENTIFIER LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = {p[2]: p[4]}

    @staticmethod
    def p_section(p):
        """section : SECTION STRING_LITERAL statement"""
        p[0] = {p[2]: p[3]}

    @staticmethod
    def p_section_statements(p):
        """section : SECTION STRING_LITERAL LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = {p[2]: p[4]}

    @staticmethod
    def p_question(p):
        """question : QUESTION IDENTIFIER"""
        p[0] = {'question': p[2], 'widget': None}

    @staticmethod
    def p_question_properties(p):
        """question : QUESTION IDENTIFIER WIDGET widget"""
        p[0] = {'question': p[2], 'widget': p[3]}

    @staticmethod
    def p_default(p):
        """default : DEFAULT type LEFT_BRACE properties RIGHT_BRACE"""
        p[0] = {'name': 'default', 'type': p[2], 'properties': p[4]}

    @staticmethod
    def p_default_widget(p):
        """default : DEFAULT type WIDGET widget"""
        p[0] = {'name': 'default', 'type': p[2], 'widget': p[3]}

    # Properties
    @staticmethod
    def p_properties(p):
        """properties   : property properties
                        | property"""
        if len(p) == 3:
            p[0] = [p[1]] + p[2]
        elif len(p) == 2:
            p[0] = [p[1]]

    @staticmethod
    def p_height(p):
        """property : HEIGHT COLON INTEGER_LITERAL"""
        p[0] = {'height': p[3]}

    @staticmethod
    def p_width(p):
        """property : WIDTH COLON INTEGER_LITERAL"""
        p[0] = {'width': p[3]}

    @staticmethod
    def p_font(p):
        """property : FONT COLON STRING_LITERAL"""
        p[0] = {'font': p[3]}

    @staticmethod
    def p_font_size(p):
        """property : FONT_SIZE COLON INTEGER_LITERAL"""
        p[0] = {'font_size': p[3]}

    @staticmethod
    def p_color(p):
        """property : COLOR COLON HEX_COLOR"""
        p[0] = {'color': p[3]}

    @staticmethod
    def p_widget(p):
        """property : WIDGET widget"""
        p[0] = {'widget': p[2]}

    # Widgets
    @staticmethod
    def p_calendar(p):
        """widget : CALENDAR"""
        p[0] = 'calendar'

    @staticmethod
    def p_checkbox(p):
        """widget : CHECKBOX"""
        p[0] = 'checkbox'

    @staticmethod
    def p_line_edit(p):
        """widget : LINE_EDIT"""
        p[0] = 'line_edit'

    @staticmethod
    def p_spinbox(p):
        """widget : SPINBOX"""
        p[0] = 'spinbox'

    @staticmethod
    def p_radio_default(p):
        """widget : RADIO"""
        p[0] = {'radio': []}

    @staticmethod
    def p_radio(p):
        """widget : RADIO LEFT_BRACKET STRING_LITERAL COMMA STRING_LITERAL RIGHT_BRACKET"""
        p[0] = {'radio': [p[3], p[4]]}

    # Types
    @staticmethod
    def p_boolean(p):
        """type : BOOLEAN"""
        p[0] = 'boolean'

    @staticmethod
    def p_decimal(p):
        """type : DECIMAL"""
        p[0] = 'decimal'

    @staticmethod
    def p_string(p):
        """type : STRING"""
        p[0] = 'string'

    @staticmethod
    def p_date(p):
        """type : DATE"""
        p[0] = 'date'

    @staticmethod
    def p_money(p):
        """type : MONEY"""
        p[0] = 'money'

    @staticmethod
    def p_integer(p):
        """type : INTEGER"""
        p[0] = 'integer'

    def p_error(self, p):
        raise SyntaxError('Syntax error at line {}, token={}'.format(p.lineno, p.type))
