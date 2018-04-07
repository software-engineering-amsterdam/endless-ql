grammar QL;
                                                                             
@lexer::members {

    void normalizeQuotedString() {
        String normalized = getText();
        normalized = normalized.substring(1, normalized.length()-1);
        normalized = normalized.replaceAll( "\\\\n","<br/>" );
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
                                                                                        
form                    :  'form'  Identifier  block                                    ;
                                                                                        
block                   :  '{'  part*  '}'                                              ;

type                    :  'money'  |  'integer'  |  'boolean'  |  'string'  |  'date'  ;
                                                                                        
part                    :  Identifier  ':'  QuotedString  type                          # PartAnswerableQuestion
                        |  Identifier  ':'  QuotedString  type  '('  expression  ')'    # PartComputedQuestion
                        |  'if'  '('  expression  ')'  block                            # PartConditionalBlock
                        |  block                                                        # PartBlock  
                        ;                                                               
unaryOperator           :  '-'  |  '+'  |  '!'    ;                                     
                                                                                        
multiplicationOperator  :  '*'   |  '/'                                                 ;
additionOperator        :  '+'   |  '-'                                                 ;
logicalOperator         :  '&&'  |  '||'                                                ;
equalityOperator        :  '=='                                                         ; 
orderingOperator        :  '>='  |  '<='  |  '>'  |  '<'                                ;
                                                                                        
                                                                                        
expression              :  '('  expression  ')'                                         # ExpressionParenthesized
                        |  unaryOperator                       expression               # UnaryOperator_Expression
                        |  expression  multiplicationOperator  expression               # Expression_MultiplicationOperator_Expression
                        |  expression        additionOperator  expression               # Expression_AdditionOperator_Expression
                        |  expression         logicalOperator  expression               # Expression_LogicalOperator_Expression
                        |  expression        equalityOperator  expression               # Expression_EqualityOperator_Expression
                        |  expression        orderingOperator  expression               # Expression_OrderingOperator_Expression
                        |  IntegerConstant                                              # ExpressionIntegerConstant
                        |  DateConstant                                                 # ExpressionDateConstant
                        |  MoneyConstant                                                # ExpressionMoneyConstant
                        |  BooleanConstant                                              # ExpressionBooleanConstant
                        |  QuotedString                                                 # ExpressionQuotedString
                        |  Identifier                                                   # Identifier
                        ;                                                               
