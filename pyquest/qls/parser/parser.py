from ply.yacc import yacc

from qls.parser.lexer import QLSLexer


class QLSParser:
    def __init__(self):
        self.__errors = []
        self.__tokens = QLSLexer.tokens
        self.__parser = None

    @property
    def errors(self):
        return self.__errors

    @property
    def tokens(self):
        return self.__tokens

    @property
    def parser(self):
        return self.__parser

    def build(self):
        self.__parser = yacc(module=self)

    def parse(self, data, lexer):
        return self.parser.parse(data, lexer)

    @staticmethod
    def p_stylesheet(production):
        """stylesheet : STYLESHEET IDENTIFIER LEFT_BRACE pages RIGHT_BRACE"""
        production[0] = ('STYLESHEET', production[2], production[4])

    # Statements
    @staticmethod
    def p_pages(production):
        """pages    : page pages
                    | page"""
        if len(production) == 3:
            production[0] = [production[1]] + production[2]
        elif len(production) == 2:
            production[0] = [production[1]]

    @staticmethod
    def p_page(production):
        """page : PAGE IDENTIFIER LEFT_BRACE sections RIGHT_BRACE
                | PAGE IDENTIFIER LEFT_BRACE sections default RIGHT_BRACE
                | PAGE IDENTIFIER LEFT_BRACE default RIGHT_BRACE"""
        if len(production) == 6:
            production[0] = ('PAGE', production[2], production[4])
        elif len(production) == 7:
            production[0] = ('PAGE', production[2], production[4], production[5])

    @staticmethod
    def p_sections(production):
        """sections : section sections
                    | section"""
        if len(production) == 3:
            production[0] = [production[1]] + production[2]
        elif len(production) == 2:
            production[0] = [production[1]]

    @staticmethod
    def p_statement(production):
        """statement    : section
                        | question"""
        production[0] = production[1]

    @staticmethod
    def p_statements(production):
        """statements   : statement statements
                        | statement"""
        if len(production) == 3:
            production[0] = [production[1]] + production[2]
        elif len(production) == 2:
            production[0] = [production[1]]

    @staticmethod
    def p_section(production):
        """section  : SECTION STRING_LITERAL LEFT_BRACE statements RIGHT_BRACE
                    | SECTION STRING_LITERAL LEFT_BRACE statements default RIGHT_BRACE
                    | SECTION STRING_LITERAL question
                    | SECTION STRING_LITERAL default"""
        if len(production) == 4:
            production[0] = ('SECTION', production[2], [production[3]])
        elif len(production) == 6:
            production[0] = ('SECTION', production[2], production[4])
        elif len(production) == 7:
            production[0] = ('SECTION', production[2], production[4], production[5])

    @staticmethod
    def p_question(production):
        """question : QUESTION IDENTIFIER"""
        production[0] = ('QUESTION', production[2])

    @staticmethod
    def p_question_properties(production):
        """question : QUESTION IDENTIFIER WIDGET widget"""
        production[0] = ('QUESTION', production[2], production[4])

    @staticmethod
    def p_default(production):
        """default : DEFAULT type LEFT_BRACE properties RIGHT_BRACE"""
        production[0] = ('DEFAULT', production[2], production[4])

    @staticmethod
    def p_default_widget(production):
        """default : DEFAULT type WIDGET widget"""
        production[0] = ('DEFAULT', production[2], production[3])

    # Properties
    @staticmethod
    def p_properties(production):
        """properties   : property properties
                        | property"""
        if len(production) == 3:
            production[0] = [production[1]] + production[2]
        elif len(production) == 2:
            production[0] = [production[1]]

    @staticmethod
    def p_height(production):
        """property : HEIGHT COLON INTEGER_LITERAL"""
        production[0] = ('HEIGHT', production[3])

    @staticmethod
    def p_width(production):
        """property : WIDTH COLON INTEGER_LITERAL"""
        production[0] = ('WIDTH', production[3])

    @staticmethod
    def p_font(production):
        """property : FONT COLON STRING_LITERAL"""
        production[0] = ('FONT', production[3])

    @staticmethod
    def p_font_size(production):
        """property : FONT_SIZE COLON INTEGER_LITERAL"""
        production[0] = ('FONT_SIZE', production[3])

    @staticmethod
    def p_color(production):
        """property : COLOR COLON HEX_COLOR"""
        production[0] = ('COLOR', production[3])

    @staticmethod
    def p_widget(production):
        """property : WIDGET widget"""
        production[0] = ('WIDGET', production[2])

    # Widgets
    @staticmethod
    def p_calendar(production):
        """widget : CALENDAR"""
        production[0] = 'CALENDAR'

    @staticmethod
    def p_checkbox(production):
        """widget : CHECKBOX"""
        production[0] = 'CHECKBOX'

    @staticmethod
    def p_line_edit(production):
        """widget : LINE_EDIT"""
        production[0] = 'LINE_EDIT'

    @staticmethod
    def p_spinbox(production):
        """widget : SPINBOX"""
        production[0] = 'SPINBOX'

    @staticmethod
    def p_radio_default(production):
        """widget : RADIO"""
        production[0] = 'RADIO'

    @staticmethod
    def p_radio(production):
        """widget : RADIO LEFT_BRACKET STRING_LITERAL COMMA STRING_LITERAL RIGHT_BRACKET"""
        production[0] = ('RADIO', production[3], production[5])

    # Types
    @staticmethod
    def p_boolean(production):
        """type : BOOLEAN"""
        production[0] = 'BOOLEAN'

    @staticmethod
    def p_decimal(production):
        """type : DECIMAL"""
        production[0] = 'DECIMAL'

    @staticmethod
    def p_string(production):
        """type : STRING"""
        production[0] = 'STRING'

    @staticmethod
    def p_date(production):
        """type : DATE"""
        production[0] = 'DATE'

    @staticmethod
    def p_money(production):
        """type : MONEY"""
        production[0] = 'MONEY'

    @staticmethod
    def p_integer(production):
        """type : INTEGER"""
        production[0] = 'INTEGER'

    def p_error(self, production):
        self.errors.append('Syntax error at line {}, token={}'.format(production.lineno, production.type))
