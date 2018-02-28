package ql.models.ast

sealed trait ASTNode

case class ASTRoot(header: ASTNode, body: ASTNode) extends ASTNode

case class ASTFormHeader() extends ASTNode
case class ASTFormBody(statements: List[ASTNode]) extends ASTNode
case class ASTQuestion(varDecl: ASTNode) extends ASTNode
case class ASTConditional() extends ASTNode
case class ASTComputation(varDecl: ASTNode, valAssign: ASTNode) extends ASTNode
case class ASTVarDecl(typeDecl: ASTNode) extends ASTNode
case class ASTTypeDecl(returnType: ASTNode) extends ASTNode
case class ASTValAssign(expression: ASTNode) extends ASTNode
case class ASTIfStatement(expression: ASTNode, statements: List[ASTNode]) extends ASTNode

case class ASTBoolean() extends ASTNode
case class ASTMoney() extends ASTNode
case class ASTNumber() extends ASTNode
case class ASTIdentifier() extends ASTNode

case class ASTLogicalCon() extends ASTNode
case class ASTLogicalDis() extends ASTNode

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

case class ASTBinary(rhs: ASTNode, lhs: ASTNode, op: ASTNode) extends ASTNode
case class ASTUnary(expr: ASTNode, op: ASTNode) extends ASTNode
