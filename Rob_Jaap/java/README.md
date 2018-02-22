##### Techniques:
* Java Spring Boot
* Maven
* Antlr


##### Dev
* Run ```./prepare_dev.sh``` to generate the parser java source files and copy
them to the correct location in the source directory. Saves trouble with imports in your IDE ;)

##### Test
```
mvn test
```
##### Run

```
mvn exec:java
```
(make sure example_ql.ql is available in the root),

or run
```
mvn package
java -jar target/qdsl-1.0-SNAPSHOT.jar example_ql.ql
```



##### Good to know
* example_ql.ql in root contains form that gets parsed correctly


##### Links

* [Antler tutorial](https://tomassetti.me/antlr-mega-tutorial/)



