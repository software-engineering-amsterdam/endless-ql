grammar QLS;

import QL_Common ;

STYLESHEET : 'stylesheet' ;
PAGE       : 'page' ;
SECTION    : 'section' ;
QUESTION   : 'question' ;

TEXT       : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); } ;

stylesheet : STYLESHEET name=Identifier CURLY_LEFT page+ CURLY_RIGHT EOF ;
page       : PAGE name=Identifier CURLY_LEFT section+ CURLY_RIGHT ;
section    : SECTION name=TEXT CURLY_LEFT question+ CURLY_RIGHT ;
question   : QUESTION name=Identifier ;