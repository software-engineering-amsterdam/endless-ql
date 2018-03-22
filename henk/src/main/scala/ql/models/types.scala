package ql.models.ast

import ql.models._

sealed trait NodeType

case class BooleanType() extends NodeType
case class MoneyType() extends NodeType
case class IntegerType() extends NodeType
case class StringType() extends NodeType
