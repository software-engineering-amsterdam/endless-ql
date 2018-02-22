#!/bin/bash
java -jar /usr/local/lib/antlr-4.7.1-complete.jar QLGrammar.g4 -o LexParser/
javac -g LexParser/*.java
export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
cd LexParser
java org.antlr.v4.gui.TestRig QLGrammar form -gui ../test