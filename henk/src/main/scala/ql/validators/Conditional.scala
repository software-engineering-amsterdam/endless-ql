package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

case class ConditionalNotBoolean(label: String) extends Exception(label)

class ConditionalValidator extends BaseValidator {
  def execute(ast: ASTNode): Option[Exception] = {
    val ifStmts = ASTCollector.getIfStatement(ast)

    ifStmts.forEach { stmt =>
      {
        val isValid = validateExpression(stmt.expression, ast)

        if (!isValid) {
          val message = "Expression in conditional has to evaluate to type Boolean"
          return Some(new ConditionalNotBoolean(message))
        }
      }
    }
    None
  }

  def isBooleanIdentifier(node: ASTIdentifier, ast: ASTNode): Boolean = {
    ValidatorHelper.infereType(node, ast) match {
      case Some(ASTBoolean()) => true
      case other => false
    }
  }

  def validateExpression(node: ASTNode, ast: ASTNode): Boolean = {
    node match {
      case binOp: ASTBinary  => validateBinOp(binOp, ast)
      case unOp: ASTUnary    => validateUnOp(unOp, ast)
      case id: ASTIdentifier => isBooleanIdentifier(id, ast)
      case other             => false
    }
  }

  def validateBinOp(binOp: ASTNode, ast: ASTNode): Boolean = {
    ValidatorHelper.infereType(binOp, ast) match {
      case Some(ASTBoolean()) => true
      case other => false
    }
  }

  def validateUnOp(unOp: ASTNode, ast: ASTNode): Boolean = {
    ValidatorHelper.infereType(unOp, ast) match {
      case Some(ASTBoolean()) => true
      case other => false
    }
  }
}
