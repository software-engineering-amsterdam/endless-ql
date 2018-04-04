package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class InvalidTypeInfered(label: String) extends Exception(label)

class TypeInferenceValidator extends BaseValidator {
  def check(ast: Statement): Option[Exception] = {
    StatementCollector.getComputations(ast).map((comp) => {
      var varDecl = comp.varDecl
      var valAssign = comp.valAssign
      ValidatorHelper.infereExpression(valAssign.expression, ast) match {
        case Some(inferedType) if inferedType == varDecl.typeDecl => None
        case None => {
          return Some(InvalidTypeInfered("no type could be inference from expression"))
        }
        case other => {
          return Some(InvalidTypeInfered("incorrect type inference"))
        }
      }
    })
    None
  }

  def execute(ast: Statement): Unit = {
    StatementCollector.getComputations(ast).map((comp) => {
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
