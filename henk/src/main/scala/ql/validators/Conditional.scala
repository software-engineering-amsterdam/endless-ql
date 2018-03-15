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
    ASTCollector.getTypeDecl(node, ast).exists { _ == ASTBoolean() }
  }

  def validateExpression(node: ASTNode, ast: ASTNode): Boolean = {
    node match {
      case binOp: ASTBinary  => validateBinOp(binOp, ast)
      case unOp: ASTUnary    => validateUnOp(unOp, ast)
      case id: ASTIdentifier => isBooleanIdentifier(id, ast)
      case other             => false
    }
  }

  def validateLogical(node: ASTBinary, ast: ASTNode): Boolean = {
    validateExpression(node.lhs, ast) && validateExpression(node.rhs, ast)
  }

  def validateRelational(node: ASTBinary, ast: ASTNode): Boolean = {
    node match {
      case ab: ASTBinary => {
        infereType(ab, ast) match {
          case None => false
          case Some(ASTBoolean()) => false
          case other => true
        }
      }
      case other => false
    }
  }

  def validateBinOp(binOp: ASTNode, ast: ASTNode): Boolean = {
    binOp match {
      case ab @ ASTBinary(_, _, op: ASTLogicalOp) => validateLogical(ab, ast)
      case ab @ ASTBinary(_, _, op: ASTRelationalOp) => validateRelational(ab, ast)
      case other                                  => false
    }
  }

  def infereType(node: ASTNode, ast: ASTNode): Option[ASTNode] = {
    node match {
      case bv: ASTIntegerValue => Some(ASTInteger())
      case bv: ASTIdentifier => ASTCollector.getTypeDecl(bv, ast)
      case bv @ ASTBinary(_,_,_) => {
        val typeRight = infereType(bv.rhs, ast)
        val typeLeft = infereType(bv.lhs, ast)

        (typeRight, typeLeft) match {
          case (Some(_lhs), Some(_rhs)) if _lhs == _rhs => Some(_lhs)
          case other => None
        }
      }
      case ASTUnary(expr: ASTNode, op: ASTUnaryMin) => {
        infereType(expr, ast)
      }
      case other => None
    }
  }

  def validateUnOp(unOp: ASTNode, ast: ASTNode): Boolean = {
    unOp match {
      case ASTUnary(expr: ASTNode, op: ASTUnaryNot) =>
        validateExpression(expr, ast)
      case ASTUnary(expr: ASTNode, op: ASTUnaryMin) =>
        infereType(expr, ast) match {
          case Some(ASTMoney()) => true
          case other => false
        }
      case other => false
    }
  }
}
