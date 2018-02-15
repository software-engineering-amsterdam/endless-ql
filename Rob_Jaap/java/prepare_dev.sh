#!/bin/bash

ANTLR_CMD="java -jar /usr/local/lib/antlr-4.7.1-complete.jar"

# Generates Antlr grammar java source files and copies to project source
$ANTLR_CMD -visitor src/main/antlr4/com/chariotit/uva/sc/qdsl/grammar/QL.g4
cp src/main/antlr4/com/chariotit/uva/sc/qdsl/grammar/*.java src/main/java/com/chariotit/uva/sc/qdsl/grammar/