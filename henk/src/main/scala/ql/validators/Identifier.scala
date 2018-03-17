package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{ Try, Success, Failure }

case class IdentifierNotDeclared(label: String) extends Exception(label)

object IdentifierValidator {
  def validate(node: ASTNode): Try[Boolean] = {
    val forms = ASTCollector.getFormBody(node)

    val declaredIdentifiers = forms.flatMap(ASTCollector.getVarDecls).flatMap(ASTCollector.getIdentifiers)
    val foundIdentifiers = forms.flatMap(ASTCollector.getTerminals).flatMap(ASTCollector.getIdentifiers)
    val undeclaredIdentifiers = foundIdentifiers.distinct diff declaredIdentifiers.distinct

    if(undeclaredIdentifiers.isEmpty) {
      Success(true)
    } else {
      Failure(new IdentifierNotDeclared(undeclaredIdentifiers(0).id))
    }
  }
}
