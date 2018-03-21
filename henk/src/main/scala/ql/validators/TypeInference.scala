package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._

case class InvalidTypeInfered(label: String) extends Exception(label)

class TypeInferenceValidator extends BaseValidator {
  def execute(ast: ASTNode): Option[Exception] = {
    val comps = ASTCollector.getComputations(ast)
    comps.getOrElse(List()).map((x) => {
      val valid = validateComputation(x, ast)
      if(!valid) {
        return Some(InvalidTypeInfered("incorrect type inference"))
      }
    })

    None
  }

  def validateComputation(comp: ASTComputation, ast: ASTNode): Boolean = {
    ValidatorHelper.infereType(comp, ast) match {
      case None => false
      case other => true
    }
  }
}
