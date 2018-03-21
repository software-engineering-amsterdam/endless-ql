grammar QLS;
import QLCommon;

// Parser rules
parameter: STRING | NUMBER | COLOR;
parametrizedWidget: WIDGET_TYPE PAREN_OPEN (parameter','?)* parameter PAREN_CLOSE;
simpleWidget: WIDGET_TYPE;
widget: simpleWidget | parametrizedWidget;
question: QUESTION IDENTIFIER (WIDGET widget)?;
propertySetting: PROPERTY_KEY PROPERTY_SEPARATOR parameter;
defaultWidgetDeclaration: DEFAULT QUESTION_TYPE BRACKET_OPEN propertySetting* WIDGET widget propertySetting* BRACKET_CLOSE
                        | DEFAULT QUESTION_TYPE WIDGET widget;
section: SECTION STRING (BRACKET_OPEN (question | defaultWidgetDeclaration | section)* BRACKET_CLOSE | question | section);
page: PAGE IDENTIFIER BRACKET_OPEN (section | defaultWidgetDeclaration)* BRACKET_CLOSE;
stylesheet: STYLESHEET IDENTIFIER BRACKET_OPEN page* BRACKET_CLOSE;

WIDGET_TYPE : 'spinbox'
            | 'checkbox'
            | 'text'
            | 'dropdown'
            | 'slider'
            | 'radio';

// Keywords
PROPERTY_KEY : 'width'
             | 'height'
             | 'font'
             | 'fontsize'
             | 'color'
             ;
DEFAULT: 'default';
SECTION: 'section';
QUESTION: 'question';
STYLESHEET: 'stylesheet';
PAGE: 'page';
WIDGET: 'widget';

PROPERTY_SEPARATOR: ':';

COLOR       : '#' [a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9];
