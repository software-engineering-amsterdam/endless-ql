package ql.models.ast

sealed trait Statement

case class Root(header: Statement, body: Statement) extends Statement
case class FormHeader(identifier: Expression) extends Statement
case class FormBody(statements: List[Statement]) extends Statement
case class Question(varDecl: VarDecl, label: String) extends Statement
case class Computation(varDecl: VarDecl, valAssign: ValAssign, label: String)
    extends Statement
case class VarDecl(typeDecl: NodeType, id: Expression) extends Statement
case class ValAssign(expression: Expression) extends Statement
case class IfStatement(expression: Expression, statements: List[Statement])
    extends Statement
