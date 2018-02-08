# DogeQL

Steps to run DogeQL:
1. Install antlr plugin in Intelij
2. add libraries to lib from http://www.antlr.org/download/antlr-4.7.1-complete.jar (need to automate)
3. Generate parser, visitor and listener from grammar.
3.1 Right click grammar file (.g4 extension) 
3.2 Select Configure ANTLR...
3.3 select checkbox generate visitor and press ok
3.4 right click grammar file again and click on Generate ANTLR...
4. Run

The program will traverse the AST and print each node (post-order).
