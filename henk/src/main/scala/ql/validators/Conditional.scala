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
        val isValid = validateExpression(stmt.expression, ast)

        if (!isValid) {
          val message = "Expression in conditional has to be of type Boolean"
          return Failure(new ConditionalNotBoolean(message))
        }
      }
    }
    Success(true)
  }

  def isBooleanIdentifier(node: ASTIdentifier, ast: ASTNode): Boolean = {
    infereType(node, ast) match {
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
    infereType(binOp, ast) match {
      case Some(ASTBoolean()) => true
      case other => false
    }
  }

  def validateUnOp(unOp: ASTNode, ast: ASTNode): Boolean = {
    infereType(unOp, ast) match {
      case Some(ASTBoolean()) => true
      case other => false
    }
  }

  def matchReturnType(op: ASTNode, nodeType: ASTNode): Option[ASTNode] = {
    (op, nodeType) match {
      case (bv1: ASTRelationalOp, ASTInteger()) => Some(ASTBoolean())
      case (bv1: ASTArithmeticOp, ASTInteger()) => Some(ASTInteger())
      case (bv1: ASTRelationalOp, ASTMoney()) => Some(ASTBoolean())
      case (bv1: ASTLogicalOp, ASTBoolean()) => Some(ASTBoolean())
      case other => None
    }
  }

  def infereType(node: ASTNode, ast: ASTNode): Option[ASTNode] = {
    node match {
      case ASTIntegerValue(_) => Some(ASTInteger())
      case ASTBoolean() => Some(ASTBoolean())
      case bv @ ASTIdentifier(_) => ASTCollector.getTypeDecl(bv, ast)
      case bv @ ASTBinary(_,_, op: ASTNode) => {
        val typeLeft = infereType(bv.lhs, ast)
        val typeRight = infereType(bv.rhs, ast)

        (typeLeft, typeRight) match {
          case (Some(lhs), Some(rhs)) if lhs == rhs => {
            matchReturnType(op, lhs)
          }
          case other => None
        }
      }
      case ASTUnary(expr: ASTNode, _) => {
        infereType(expr, ast)
      }
      case other => None
    }
  }
}
