package ql.models.ast

import ql.models._

sealed trait NodeType

case object BooleanType extends NodeType
case object MoneyType extends NodeType
case object IntegerType extends NodeType
case object StringType extends NodeType
