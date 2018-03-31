package ql.visitors

import grammar._
import ql.models.ast._

class TypeVisitor extends QLBaseVisitor[NodeType] {

  override def visitTypeDecl(ctx: QLParser.TypeDeclContext): NodeType =
    ctx.getText match {
      case "boolean" => BooleanType()
      case "money"   => MoneyType()
      case "integer" => IntegerType()
      case "string"  => StringType()
    }
}
