# QLS Implementation
Our QL program uses antlr for creating a parse tree based on the grammar. The parse tree is then visited and the AST for the program is created.
The AST is used to generate the formatting for the questions. For more information on how the AST works and what nodes are part of it, please look at the classes. Traversals through the AST are done by adding methods to the classes, and calling the methods for the respective children. We did this so the language can be customized more easily.

## Syntax
Here is an example of viable syntax for the QLS:
stylesheet taxOfficeExample
```
{
  page Housing
  {
    section "Buying"
    {
      question hasBoughtHouse  
        widget checkbox 
    }
    section "Loaning"  
      question hasMaintLoan
  }

  page Selling
  { 
    section "Selling"
    {
      question hasSoldHouse
        widget radio("Yes", "No") 
      section "You sold a house"
      {
        question sellingPrice
          widget spinbox(0, 100)
        question privateDebt
          widget slider(4, 20)
        question valueResidue
        default money
        {
          width: 400
          font: "Arial" 
          fontsize: 14
          color: #999999
          widget spinbox(3, 30)
        }        
      }
    }
    default boolean widget radio("Yes", "No")
  }  
}
```
Default styling can be overridden on a per type and widget basis. We added minimal and maxium value options for the spinbox and slider widgets (as is shown in the example). 

## Typechecker
The typechecker checks if every default that is declared with a question, actually contains a widget, and it checks if the widgets actually support the questio type. All QL questions must be defined in the QLS file, and can only be used once. 
