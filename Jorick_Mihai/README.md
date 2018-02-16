Software Construction
================================

Students :
 * Jorick van Rhenen   (jvrhenen)   11353341
 * Mihai Onofei        (mihai303)   11407360       


This repository contains the source code for the QL programming language.
Our implementation language of choice is [Java v1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and our parsing / lexer is based on [Antlr4](http://www.antlr.org/).

Here is a link to the [QL Grammar](src/org/uva/jomi/ql/parser/antlr/QL.g4).

#### Build instructions

We use the [Eclipse Oxygen](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/oxygen2) IDE for development. In order to build the project one must import the project inside Eclipse.

In order to run the project needs  the [Antlr4 Java Runtime](http://www.antlr.org/download/antlr-runtime-4.7.1.jar). On Unix environments one can use the following steps :

```
# cd Jorick_Mihai
# mkdir lib && cd lib/
# wget http://www.antlr.org/download/antlr-runtime-4.7.1.jar
``` 

After the Antlr4 runtime is in the correct location one can open the [QL.java](src/org/uva/jomi/ql/parser/antlr/QL.g4) file and run it. The QL programming language expects and input file, as such the user must update the default [Run Configuration](https://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.cdt.doc.user%2Ftasks%2Fcdt_t_run_com.htm) and
 provide an input file (we provide two examples as a reference: [example-1.ql](example-1.ql) and [example-1.ql](example-2.ql)).  