package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class InvalidTypeInfered(label: String) extends Exception(label)

class TypeInferenceValidator extends BaseValidator {
  def execute(ast: Root): Unit = {
    val statements = FormCollector.getStatements(ast)

    statements.flatMap(StatementCollector.getComputations(_)).map((comp) => {
      var varDecl = comp.varDecl
      var valAssign = comp.valAssign
      (ValidatorHelper.infereExpression(valAssign.expression, ast), varDecl.typeDecl)
    })
    .find{ case (typeLeft, typeRight) => {
      typeLeft.getOrElse(None) != typeRight
    }}
    .map{ case (_, _) => {
      val message = "Computation is getting a different type assigned than declared!"
      throw InvalidTypeInfered(message)
    }}
  }
}
