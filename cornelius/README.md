# Software Construction

## Colaborators:
* Cornelius Ries

## Project Technologies
* Maven as build system
* XText IDE for Language Development
* JavaFX for the UI part

## Layout & Design
* seperate projects in xtext-mvn
* org.uva.sc.cr.ql.parent -> questionnaire language
* org.uva.sc.cr.qsl.parent -> stylesheet language

## How to run QL Interpreter

The interpreter expects a .ql file as argument. A sample .ql file is provided in the resources folder.

It can be run via maven and/or eclipse:

### Maven

* open bash or shell
* go into the folder `org.uva.sc.cr.ql.parent/org.uva.sc.cr.ql`
* run `mvn clean compile`
* run `mvn exec:java@interpreter -Dexec.args="src/main/resources/test.ql"`

### Eclipse

* Download the DSL distribution of maven
* Import the projects (enable nested scan for import)
* Run the file `GenerateQL.mwe2` as `MWE2 Workflow` to generate missing code
* Create a new launch configuration for Java Application
  * As main class select `QLInterpreter.xtend`
  * Add `src/main/resources/test.ql` as arguments

## How to run QSL Interpreter

The interpreter expects a .qsl file as argument. A sample .qsl file is provided in the resources folder.

It can be run via maven and/or eclipse:

### Maven

* open bash or shell
* go into the folder `org.uva.sc.cr.ql.parent`
* run `mvn clean install`
* go into the folder `org.uva.sc.cr.qsl.parent/org.uva.sc.cr.qsl/`
* run `mvn clean compile`
* run `mvn exec:java@interpreter -Dexec.args="src/main/resources/test.qsl"`

### Eclipse

TODO
