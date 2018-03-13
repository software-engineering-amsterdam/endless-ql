package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

case class ConditionalNotBoolean(label: String) extends Exception(label)

object ConditionalValidator {
  def validate(ast: ASTNode): Try[Boolean] = {
    val ifStmts = ASTCollector.getIfStatement(ast)

    ifStmts.forEach { stmt =>
      {
        val isValid = stmt.expression match {
          case binOp: ASTBinary  => validateBinOp(binOp, ast)
          case id: ASTIdentifier => validateIdentifier(id, ast)
          case other             => false
        }

        if (!isValid) {
          val message = "Expression in conditional has to be of type Boolean"
          return Failure(new ConditionalNotBoolean(message))
        }
      }
    }
    Success(true)
  }
  // case other => Failure(new ConditionalNotBoolean("nope"))
  // binop -> kijk of lhs & rhs vergelijkbaar zijn, geef type door naar boven.
  // identifier -> kijk of de identifier een boolean value heeft.

  def validateIdentifier(node: ASTIdentifier, ast: ASTNode): Boolean = {
    val forms = ASTCollector.getFormBody(ast)
    val formVarDecls = forms.map(ASTCollector.getVarDecls).flatten
    val varDecl = formVarDecls.filter(vd => vd.id == node)

    varDecl(0).typeDecl match {
      case ASTBoolean() => true
      case other        => false
    }
  }

  def validateBinOp(binOp: ASTNode, ast: ASTNode): Boolean = {
    binOp match {
      case ASTBinary(lhs: ASTIdentifier,
                     rhs: ASTIdentifier,
                     op: ASTLogicalCon) => {
        val lhsType = ASTCollector.getTypeDecl(lhs, ast)
        val rhsType = ASTCollector.getTypeDecl(rhs, ast)
        (lhsType, rhsType) match {
          case (Some(ASTBoolean()), Some(ASTBoolean())) => true
          case other                                    => false
        }
      }
      case other => true
    }
  }
}
