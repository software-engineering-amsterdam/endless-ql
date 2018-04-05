package ql.models.ast

sealed trait FormElement
case class Root(header: Header, body: Body) extends FormElement
case class Header(identifier: Identifier) extends FormElement
case class Body(statements: List[Statement]) extends FormElement

sealed trait Statement

case class Question(varDecl: VarDecl, label: String) extends Statement
case class Computation(varDecl: VarDecl, valAssign: ValAssign, label: String)
    extends Statement
case class VarDecl(typeDecl: NodeType, id: Identifier) extends Statement
case class ValAssign(expression: Expression) extends Statement
case class IfStatement(expression: Expression, statements: List[Statement])
    extends Statement
case class ElseStatement(statements: List[Statement]) extends Statement
case class ConditionalStatement(ifStmt: IfStatement, elseStmt: Option[ElseStatement]) extends Statement
