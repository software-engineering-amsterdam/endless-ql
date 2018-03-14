package ql.models.ast

sealed trait ASTNode {
  def flatten(): List[ASTNode] = {
    List(null)
  }
}

sealed trait ASTNonTerminal extends ASTNode
sealed trait ASTLogicalOp extends ASTNode
sealed trait ASTRelationalOp extends ASTNode

case class ASTRoot(header: ASTNode, body: ASTNode) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(header, body)
  }
}

case class ASTFormHeader(identifier: ASTNode) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(identifier)
  }
}

case class ASTFormBody(statements: List[ASTNode]) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    statements
  }
}

case class ASTQuestion(varDecl: ASTNode, label: String) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(varDecl)
  }
}

case class ASTComputation(varDecl: ASTNode, valAssign: ASTNode, label: String)
    extends ASTNonTerminal

case class ASTVarDecl(typeDecl: ASTNode, id: ASTNode) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(typeDecl, id)
  }
}

case class ASTTypeDecl(returnType: ASTNode) extends ASTNode
case class ASTValAssign(expression: ASTNode) extends ASTNonTerminal
case class ASTIfStatement(expression: ASTNode, statements: List[ASTNode])
    extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(expression) ++ statements
  }
}

case class ASTBoolean() extends ASTNode
case class ASTMoney() extends ASTNode
case class ASTNumber() extends ASTNode
case class ASTIdentifier(id: String) extends ASTNode

case class ASTLogicalCon() extends ASTLogicalOp
case class ASTLogicalDis() extends ASTLogicalOp

case class ASTUnaryNot() extends ASTNode
case class ASTUnaryMin() extends ASTNode

case class ASTRelationalLT() extends ASTNode
case class ASTRelationalLTE() extends ASTNode
case class ASTRelationalGT() extends ASTNode
case class ASTRelationalGTE() extends ASTNode
case class ASTRelationalNE() extends ASTNode
case class ASTRelationalEQ() extends ASTNode

case class ASTAdd() extends ASTNode
case class ASTMin() extends ASTNode
case class ASTMul() extends ASTNode
case class ASTDiv() extends ASTNode

case class ASTBinary(rhs: ASTNode, lhs: ASTNode, op: ASTNode) extends ASTNonTerminal {
  // todo: exprs that contain exprs aren't traversed.
  override def flatten(): List[ASTNode] = {
    List(lhs, rhs, op)
  }
}
case class ASTUnary(expr: ASTNode, op: ASTNode) extends ASTNonTerminal {
  override def flatten(): List[ASTNode] = {
    List(expr, op)
  }
}
