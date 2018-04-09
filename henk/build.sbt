name := "henk"
version := "1.0"

antlr4Settings

antlr4Version in Antlr4 := "4.7.1"
antlr4PackageName in Antlr4 := Some("grammar")
antlr4GenListener in Antlr4 := true // default: true
antlr4GenVisitor in Antlr4 := true // default: false

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.7.1",
  "org.antlr" % "stringtemplate" % "3.2",
  "org.scalafx" %% "scalafx" % "8.0.144-R12",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "org.mockito" % "mockito-all" % "1.8.4"
)
fork := true
