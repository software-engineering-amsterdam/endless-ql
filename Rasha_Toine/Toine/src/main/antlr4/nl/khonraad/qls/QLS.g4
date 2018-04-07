grammar QLS;

@lexer::members {

    void normalizeQuotedString() {
        String normalized = getText();
        normalized = normalized.substring(1, normalized.length()-1);
        normalized = normalized.replaceAll( "\\\\n","<br/>" );
        normalized = normalized.replaceAll("\\\\(.)", "$1");
        setText(normalized);
    }
}      

WS                      :  [ \t\r\n\u000C]+  ->  channel(HIDDEN)                                    ;
Comment                 :  '/*'  .*?  '*/'   ->  channel(HIDDEN)                                    ;
LineComment             :  '//'  ~[\r\n]*    ->  channel(HIDDEN)                                    ;
                                                                                                    
QuotedString            :  '"' (~[\\"] | '\\' [\\"()])* '"' { normalizeQuotedString(); }            ;
                                                                                                    
BooleanConstant         :  'True'  |  'False'                                                       ;  
Identifier              :  [_a-zA-Z]  [_a-zA-Z0-9]*                                                 ;  
MoneyConstant           :  [0-9]+  '.'  [0-9][0-9]                                                  ;  
DateConstant            :  [0-9][0-9]  '/'  [0-9][0-9]  '/'  [0-9][0-9][0-9][0-9]                   ;  
IntegerConstant         :  [0-9]+                                                                   ;  
HexConstant             : '#'  [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] ;              
                                                                                                    
type                    :  'money'  |  'integer'  |  'boolean'  |  'string'  |  'date'              ;


                                                                                        
stylesheet              : 'stylesheet' Identifier '{' page+ defaultStyle* '}'  
                        ;
                        
page                    : 'page' Identifier '{' section+ defaultStyle* '}' 
                        ;
                        
section                 : 'section' QuotedString sectionElements 
                        | 'section' QuotedString sectionElement                                     
                        ;
                                        
sectionElements         : '{' sectionElement+ defaultStyle* '}'
                        ;                        
                        
sectionElement          : section  
                        | question  
                        ;
                        
question                : 'question' Identifier styling? 
                        ;
                        
defaultStyle            : 'default' questionType styling 
                        ;
                         
questionType            : type
                        ;
                        
styling                 : style
                        | '{' style+ '}' 
                        ;
                        
style                   : 'widget'          widgetType              # givenWidgetType                                             
                        | 'width'       ':' IntegerConstant         # givenWidth
                        | 'color'       ':' HexConstant             # givenColor
                        | 'font'        ':' QuotedString            # givenFont 
                        | 'fontsize'    ':' IntegerConstant         # givenFontsize
                        ;
                        
widgetType              : 'checkbox'                                                                    
                        | 'spinbox' 
                        | 'combo'   '(' QuotedString ',' QuotedString ')'       
                        | 'radio'   '(' QuotedString ',' QuotedString ')'        
                        ;

