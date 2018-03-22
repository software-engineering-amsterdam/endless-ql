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
          case unOp: ASTUnary    => validateUnOp(unOp, ast)
          case id: ASTIdentifier => isBooleanIdentifier(id, ast)
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

  def isBooleanIdentifier(node: ASTIdentifier, ast: ASTNode): Boolean = {
    ASTCollector.getTypeDecl(node, ast) match {
      case Some(ASTBoolean()) => true
      case other              => false
    }
  }

  def validateLogical(node: ASTBinary, ast: ASTNode): Boolean = {
    node match {
      case ASTBinary(lhs: ASTIdentifier,
                     rhs: ASTIdentifier,
                     op: ASTLogicalCon) => {
        isBooleanIdentifier(lhs, ast) && isBooleanIdentifier(rhs, ast)
      }
      case ASTBinary(lhs: ASTBinary, rhs: ASTIdentifier, op: ASTLogicalCon) => {
        validateBinOp(lhs, ast) && isBooleanIdentifier(rhs, ast)
      }
      case ASTBinary(lhs: ASTIdentifier, rhs: ASTBinary, op: ASTLogicalCon) => {
        validateBinOp(rhs, ast) && isBooleanIdentifier(lhs, ast)
      }
      case ASTBinary(lhs: ASTBinary, rhs: ASTBinary, op: ASTLogicalCon) => {
        validateBinOp(lhs, ast) && validateBinOp(lhs, ast)
      }
      case other => false
    }
  }

  def validateBinOp(binOp: ASTNode, ast: ASTNode): Boolean = {
    binOp match {
      case ab @ ASTBinary(_, _, ASTLogicalDis()) => validateLogical(ab, ast)
      case ab @ ASTBinary(_, _, ASTLogicalCon()) => validateLogical(ab, ast)
      case other                                 => false
    }
  }

  def validateUnOp(unOp: ASTNode, ast: ASTNode): Boolean = {
    unOp match {
      case ASTUnary(expr: ASTIdentifier, op: ASTUnaryNot) =>
        isBooleanIdentifier(expr, ast)
      case ASTUnary(expr: ASTUnary, op: ASTUnaryNot) => validateUnOp(expr, ast)
      case ASTUnary(expr: ASTBinary, op: ASTUnaryNot) =>
        validateBinOp(expr, ast)
      case other => false
    }
  }
}
