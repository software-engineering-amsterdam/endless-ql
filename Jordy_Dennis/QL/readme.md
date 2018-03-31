# QL Implementation
Our QL program uses antlr for creating a parse tree based on the grammar. The parse tree is then visited and the AST for the program is created.
The AST is used to generate the questions, and store the values of the questions. For more information on how the AST works and what nodes are part of it, please look at the classes. Traversals through the AST are done by adding methods to the classes, and calling the methods for the respective children. We did this so the language can be customized more easily.

## Syntax
Here is an example of viable syntax for the program:
```
form taxOfficeExample
{ 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    
  if (hasSoldHouse)
  {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = 
        (sellingPrice - privateDebt)
  }
  
}
```

Note that the type money is immediately converted to a type float, which is also supported. The supported types with their keywords are: 
```
	Types:		Keywords:
	integer ->	int, integer
	boolean -> 	boolean, bool
	string	-> 	string, str
	float	-> 	money, float
```


## Typechecker

The AST checks types for the expressions which can be declared in a condition or in an assignment. If the operators do not support the variable types an error will be thrown. Boolean operators are always supported, since an unset variable (or 0), will result in a False, and True otherwise. 


## Limitations
We did not include the scoping of questions in our language to reduce complexity. Questions and assignment variables can only be declared once, and double declaration of variables/questions will result in an error. 