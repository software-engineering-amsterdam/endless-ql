package qls.models.ast

import ql.models.ast.{ Identifier }

sealed trait Statement

case class Root(header: RootHeader, body: RootBody) extends Statement
case class RootHeader(identifier: Identifier) extends Statement
case class RootBody(pages: List[Page]) extends Statement
case class Page(identifier: Identifier, content: List[DisplayItem]) extends Statement
