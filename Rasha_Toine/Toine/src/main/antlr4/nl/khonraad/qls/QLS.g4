grammar QLS;                                                                              

@lexer::members {                                                                         
    void normalizeQuotedString() {                                                        

        String normalized = getText();                                                    
        normalized = normalized.substring(1, normalized.length()-1);                      
        normalized = normalized.replaceAll("\\\\(.)", "$1");                              
        setText(normalized);                                                              
    }                                                                                     
}                                                                                         

WS                      :  [ \t\r\n\u000C]+  ->  channel(HIDDEN)                        ; 
Comment                 :  '/*'  .*?  '*/'   ->  channel(HIDDEN)                        ; 
LineComment             :  '//'  ~[\r\n]*    ->  channel(HIDDEN)                        ; 
QuotedString            :  '"' (~[\\"] | '\\' [\\"()])* '"' { normalizeQuotedString(); }; 
BooleanConstant         :  'True'  |  'False'                                           ; 
Identifier              :  [_a-zA-Z]  [_a-zA-Z0-9]*                                     ; 
MoneyConstant           :  [0-9]+  '.'  [0-9][0-9]                                      ; 
DateConstant            :  [0-9][0-9]  '/'  [0-9][0-9]  '/'  [0-9][0-9][0-9][0-9]       ; 
IntegerConstant         :  [0-9]+                                                       ; 
Hex                     :  '#' [0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F]         ; 

type                    :  'money'  |  'integer'  |  'boolean'  |  'string'  |  'date'  ;

stylesheet              : 'stylesheet' Identifier ( page | '{' page* '}' )              ; 

page                    : 'page' Identifier (statement | '{' statement* '}')            ;

section                 : 'section' QuotedString (statement | '{' statement* '}')       ;

question                : 'question' Identifier widget?                                 ;

statement               :  section                                                        
                        |  question                                              
                        |  defaultstyle                                                 ;
 
defaultstyle            : 'default' type (widget | '{' attribute* widget? '}')          ;

attribute               : 'width' ':' IntegerConstant                                     
                        | 'color' ':' Hex                                                 
                        | 'fontsize' ':' IntegerConstant                                  
                        | 'font' ':' QuotedString                                       ;

widget                  : 'widget' 'radio' '(' QuotedString ',' QuotedString ')'         
                        | 'widget' 'textbox'                                             
                        | 'widget' 'dropdown' '(' QuotedString ',' QuotedString ')'      
                        | 'widget' 'spinbox'                                             
                        | 'widget' 'checkbox'                                            
                        | 'widget' 'slider' '(' IntegerConstant ',' IntegerConstant ')' ;

                                                                                          
