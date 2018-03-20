package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

object ValidatorHelper {
  def matchReturnType(op: ASTNode, nodeType: ASTNode): Option[ASTNode] = {
    (op, nodeType) match {
      case (bv1: ASTRelationalOp, ASTInteger()) => Some(ASTBoolean())
      case (bv1: ASTRelationalOp, ASTMoney()) => Some(ASTBoolean())
      case (bv1: ASTArithmeticOp, ASTInteger()) => Some(ASTInteger())
      case (bv1: ASTArithmeticOp, ASTMoney()) => Some(ASTMoney())
      case (bv1: ASTLogicalOp, ASTBoolean()) => Some(ASTBoolean())
      case (bv1: ASTEqualityOp, _) => Some(ASTBoolean())
      case other => None
    }
  }

  def infereType(node: ASTNode, ast: ASTNode): Option[ASTNode] = {
    node match {
      case ASTIntegerValue(_) => Some(ASTInteger())
      case ASTBooleanValue(_) => Some(ASTBoolean())
      case ASTStringValue(_) => Some(ASTString())
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
      case ASTVarDecl(ntype: ASTNode, _) => Some(ntype)
      case ASTValAssign(expr: ASTNode) => infereType(expr, ast)
      case ASTComputation(varDecl: ASTNode, valAssign: ASTNode, _) => {
        val typeDecl = infereType(varDecl, ast)
        val typeAssign = infereType(valAssign, ast)

        (typeDecl, typeAssign) match {
          case (Some(lhs), Some(rhs)) if lhs == rhs => Some(rhs)
          case other => None
        }
      }
      case other => None
    }
  }
}
