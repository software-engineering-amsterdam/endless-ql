from ply.yacc import yacc
from qls.parser.lexer import QLSLexer


class QLSParser:
    def __init__(self):
        self.tokens = QLSLexer.tokens
        self.parser = yacc(module=self)

    def parse(self, data, lexer):
        return self.parser.parse(data, lexer)

    @staticmethod
    def p_stylesheet(p):
        """stylesheet : STYLESHEET IDENTIFIER block"""
        p[0] = ('STYLESHEET', p[2], p[3])

    @staticmethod
    def p_block(p):
        """block : LEFT_BRACE statements RIGHT_BRACE"""
        p[0] = p[2]

    # Statements
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
                        | default"""
        p[0] = p[1]

    @staticmethod
    def p_page(p):
        """page : PAGE IDENTIFIER block"""
        p[0] = ('PAGE', p[2], p[3])

    @staticmethod
    def p_section(p):
        """section  : SECTION STRING_LITERAL statement
                    | SECTION STRING_LITERAL block"""
        p[0] = ('SECTION', p[2], p[3])

    @staticmethod
    def p_question(p):
        """question : QUESTION IDENTIFIER"""
        p[0] = ('QUESTION', p[2])

    @staticmethod
    def p_question_properties(p):
        """question : QUESTION IDENTIFIER WIDGET widget"""
        p[0] = ('QUESTION', p[2], p[4])

    @staticmethod
    def p_default(p):
        """default : DEFAULT type LEFT_BRACE properties RIGHT_BRACE"""
        p[0] = ('DEFAULT', p[2], p[4])

    @staticmethod
    def p_default_widget(p):
        """default : DEFAULT type WIDGET widget"""
        p[0] = ('DEFAULT', p[2], p[3])

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
        p[0] = ('HEIGHT', p[3])

    @staticmethod
    def p_width(p):
        """property : WIDTH COLON INTEGER_LITERAL"""
        p[0] = ('WIDTH', p[3])

    @staticmethod
    def p_font(p):
        """property : FONT COLON STRING_LITERAL"""
        p[0] = ('FONT', p[3])

    @staticmethod
    def p_font_size(p):
        """property : FONT_SIZE COLON INTEGER_LITERAL"""
        p[0] = ('FONT_SIZE', p[3])

    @staticmethod
    def p_color(p):
        """property : COLOR COLON HEX_COLOR"""
        p[0] = ('COLOR', p[3])

    @staticmethod
    def p_widget(p):
        """property : WIDGET widget"""
        p[0] = ('WIDGET', p[2])

    # Widgets
    @staticmethod
    def p_calendar(p):
        """widget : CALENDAR"""
        p[0] = 'CALENDAR'

    @staticmethod
    def p_checkbox(p):
        """widget : CHECKBOX"""
        p[0] = 'CHECKBOX'

    @staticmethod
    def p_line_edit(p):
        """widget : LINE_EDIT"""
        p[0] = 'LINE_EDIT'

    @staticmethod
    def p_spinbox(p):
        """widget : SPINBOX"""
        p[0] = 'SPINBOX'

    @staticmethod
    def p_radio_default(p):
        """widget : RADIO"""
        p[0] = 'RADIO'

    @staticmethod
    def p_radio(p):
        """widget : RADIO LEFT_BRACKET STRING_LITERAL COMMA STRING_LITERAL RIGHT_BRACKET"""
        p[0] = ('RADIO', p[3], p[5])

    # Types
    @staticmethod
    def p_boolean(p):
        """type : BOOLEAN"""
        p[0] = 'BOOLEAN'

    @staticmethod
    def p_decimal(p):
        """type : DECIMAL"""
        p[0] = 'DECIMAL'

    @staticmethod
    def p_string(p):
        """type : STRING"""
        p[0] = 'STRING'

    @staticmethod
    def p_date(p):
        """type : DATE"""
        p[0] = 'DATE'

    @staticmethod
    def p_money(p):
        """type : MONEY"""
        p[0] = 'MONEY'

    @staticmethod
    def p_integer(p):
        """type : INTEGER"""
        p[0] = 'INTEGER'

    def p_error(self, p):
        raise SyntaxError('Syntax error at line {}, token={}'.format(p.lineno, p.type))
