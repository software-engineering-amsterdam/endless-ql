# NIRO QL/QLS Interpreter
An interpreter for the questionnaire language QL/QLS language.

## Students
* Nico Tromp
* Rocco Andela

## Introduction
There are actually two applications, one that can only interpret QL files and one that is capable of interpreting both
QL and QLS files. The QLS capable version of the application detects automatically if there is a QLS file that has the
same name as the QL file. If one is found the QLS file is also loaded and interpreted. If there is no accompanying QLS
file for a QL file, the application switches to a pure QL mode.  

## How to build
There are two ways to build the application.  
* Create a executable JAR file
* Create a native executable application

### Create executable jar file
* To create a QLS capable application, run `mvn jfx:jar`
* To create a QL capable application, run `mvn -Pql jfx:jar`  

### Create executable native application
* To create a QLS capable application, run `mvn jfx:native`
* To create a QL capable application, run `mvn -Pql jfx:native` 

## How to run
Depending on the exact type of executable you have build.

### Executable jar
Locate the jar-file. It should be at `target/jfx/app`. Then run `java -jar <jar-file>`  
For example: `java -jar target/jfx/app/ql-forms.jar` in order to run the QL version of the application.
Or `java -jar target/jfx/app/qls-forms.jar` if you want to run the QLS capable application. 

### Executable native application
Depending on the platform you are on locate the appropriate installer at `target/jfx/native`.
