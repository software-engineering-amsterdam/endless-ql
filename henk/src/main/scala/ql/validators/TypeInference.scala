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
      (varDecl.typeDecl, ValidatorHelper.infereExpression(valAssign.expression, ast))
    })
    .find{ case (typeLeft, typeRight) => {
      typeLeft != typeRight.getOrElse(None)
    }}
    .map{ case (typeLeft, typeRight) => {
      val message = s"""
        Computation is declared as type '${typeLeft}' while the value assigned
        to it is of type '${typeRight.getOrElse(None)}'!
      """
      throw InvalidTypeInfered(message)
    }}
  }
}
