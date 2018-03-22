package ql.models.ast

sealed trait Statement

case class Root(header: Statement, body: Statement) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // List(header, body)
  // }
// }

case class FormHeader(identifier: Expression) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // List(identifier)
  // }
// }

case class FormBody(statements: List[Statement]) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // statements
  // }
// }

case class Question(varDecl: Statement, label: String) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // List(varDecl)
  // }
// }

case class Computation(varDecl: Statement, valAssign: Statement, label: String)
    extends Statement
    // {
  // override def flatten(): List[ASTNode] = {
    // List(varDecl, valAssign)
  // }
// }

case class VarDecl(typeDecl: NodeType, id: Expression) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // List(typeDecl, id)
  // }
// }

case class ValAssign(expression: Expression) extends Statement
// {
  // override def flatten(): List[ASTNode] = {
    // List(expression)
  // }
// }

case class IfStatement(expression: Expression, statements: List[Statement])
    extends Statement
    // {
  // override def flatten(): List[ASTNode] = {
    // List(expression) ++ statements
  // }
// }
