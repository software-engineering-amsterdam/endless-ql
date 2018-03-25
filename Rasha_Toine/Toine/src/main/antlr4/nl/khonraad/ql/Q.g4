grammar Q;

WS                      :  [ \t\r\n\u000C]+  ->  channel(HIDDEN)                          ;
Comment                 :  '/*'  .*?  '*/'   ->  channel(HIDDEN)                          ;
LineComment             :  '//'  ~[\r\n]*    ->  channel(HIDDEN)                          ;
            
Type                    :  'money'  |  'integer'  |  'boolean'  |  'string'  |  'date'    ;
                  
QuotedString            :  '"'  .+?  '"'                                                  ;  
BooleanConstant         :  'True'  |  'False'                                             ;  
Identifier              :  [_a-zA-Z]  [_a-zA-Z0-9]*                                       ;  
MoneyConstant           :  [0-9]+  '.'  [0-9][0-9]                                        ;  
DateConstant            :  [0-9][0-9]  '/'  [0-9][0-9]  '/'  [0-9][0-9][0-9][0-9]         ;  
IntegerConstant         :  [0-9]+                                                         ;  
        
form                    :  'form'  Identifier  block                                      ;
                      
block                   :  '{'  part*  '}'                                                ;
        
part                    :  Identifier  ':'  QuotedString  Type                            # PartAnswerableQuestion
                        |  Identifier  ':'  QuotedString  Type  '('  expression  ')'      # PartComputedQuestion
                        |  'if'  '('  expression  ')'  block                              # PartConditionalBlock
                        |  block                                                          # PartBlock  
                        ;  
unaryOperator           :  '-'  |  '+'  |  '!'    ;

multiplicationOperator  :  '*'   |  '/'                                                   ;
additionOperator        :  '+'   |  '-'                                                   ;
logicalOperator         :  '&&'  |  '||'                                                  ;
equalityOperator        :  '=='                                                           ; 
orderingOperator        :  '>='  |  '<='  |  '>'  |  '<'                                  ;

  
expression              :  '('  expression  ')'                                           # ExpressionParenthesized
                        |  unaryOperator                       expression                 # UnaryOperator_Expression
                        |  expression  multiplicationOperator  expression                 # Expression_MultiplicationOperator_Expression
                        |  expression        additionOperator  expression                 # Expression_AdditionOperator_Expression
                        |  expression         logicalOperator  expression                 # Expression_LogicalOperator_Expression
                        |  expression        equalityOperator  expression                 # Expression_EqualityOperator_Expression
                        |  expression        orderingOperator  expression                 # Expression_OrderingOperator_Expression
                        |  IntegerConstant                                                # ExpressionIntegerConstant
                        |  DateConstant                                                   # ExpressionDateConstant
                        |  MoneyConstant                                                  # ExpressionMoneyConstant
                        |  BooleanConstant                                                # ExpressionBooleanConstant
                        |  QuotedString                                                   # ExpressionQuotedString
                        |  Identifier                                                     # Identifier
                        ;