# Software Construction
## Members
- Hector Stenger, 10398872

## Requirements
The following prerequisite are needed to setup the project:

1. ANTLR4
2. SBT 4.7.1

## Running
Create JAR:

```
sbt assembly
```

Run JAR:
```
java -jar <location target jar> <ql file location>
```

## Testing
To run the test suite do the following:

```
sbt test
```
