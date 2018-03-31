package qls.models.ast

sealed trait NodeType
case object IntegerType extends NodeType
case object BooleanType extends NodeType
case object StringType extends NodeType
