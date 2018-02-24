package ql.models

sealed trait ASTNode

case class ASTRoot(header: ASTNode, body: ASTNode) extends ASTNode
case class ASTFormHeader(label: String) extends ASTNode
case class ASTFormBody(statements: List[ASTNode]) extends ASTNode
case class ASTQuestion(label: String, varDecl: ASTNode) extends ASTNode
case class ASTConditional() extends ASTNode
case class ASTVarDecl(label: String, typeDecl: ASTNode, valAssign: ASTNode) extends ASTNode
case class ASTTypeDecl(returnType: ASTNode) extends ASTNode
case class ASTBoolean() extends ASTNode
