#!/bin/bash
java -jar /usr/local/lib/antlr-4.7.1-complete.jar QLGrammar.g4 -o LexParser/
javac -g LexParser/*.java
cd LexParser
java org.antlr.v4.gui.TestRig QLGrammar form -gui ../test