# Software Construction

## Colaborators:
* Cornelius Ries

## Project Technologies
* Maven as build system
* XText IDE for Language Development
* JavaFX for the UI part
* Joda-Money to implement the Money Pattern (http://www.joda.org/joda-money/)
* ControlsFX for the Wizard to implement Paged style (http://fxexperience.com/controlsfx/)
 

## Layout & Design
* seperate projects in xtext-mvn
* org.uva.sc.cr.ql.parent -> questionnaire language
* org.uva.sc.cr.qsl.parent -> stylesheet language

## How to run QL Interpreter

The interpreter expects you to load a .ql file as argument. A sample .ql file is provided in the resources folder.

It can be run via maven and/or eclipse:

### Maven

* open bash or shell
* go into the folder `org.uva.sc.cr.ql.parent/org.uva.sc.cr.ql`
* run `mvn clean compile`
* run `mvn exec:java@interpreter`

### Eclipse

* Download the DSL distribution of eclipse
* Import the projects (enable nested scan for import)
* Run the file `GenerateQL.mwe2` as `MWE2 Workflow` to generate missing code
* Run the file `QLInterpreter.xtend` as `Java Application`

## How to run QSL Interpreter

The interpreter expects you to load a .qsl file. A sample .qsl file is provided in the resources folder.

It can be run via and/or eclipse:

### Maven 

* open bash or shell
* go into the folder `org.uva.sc.cr.ql.parent`
* run `mvn clean install`
* go into the folder `org.uva.sc.cr.qsl.parent/org.uva.sc.cr.qsl/`
* run `mvn clean compile`
* run `mvn exec:java@interpreter`

### Eclipse

* Download the DSL distribution of eclipse
* Import the projects (enable nested scan for import)
* Run the file `GenerateQSL.mwe2` as `MWE2 Workflow` to generate missing code
* Run the file `QSLInterpreter.xtend` as `Java Application`


