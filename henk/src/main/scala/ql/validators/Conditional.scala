package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class ConditionalNotBoolean(label: String) extends Exception(label)

class ConditionalValidator extends BaseValidator {
  def check(root: Statement): Option[Exception] = {
    val ifStmts = StatementCollector.getIfStatements(root)

    ifStmts.forEach { stmt =>
      {
        val expressionType = ValidatorHelper.infereExpression(stmt.expression, root)
        
        expressionType match {
          case Some(BooleanType) => None
          case Some(IntegerType) => {
            val message = "Conditional type evaluation can't result in Integer"
            return Some(new ConditionalNotBoolean(message))
          }
          case Some(StringType) => {
            val message = "Conditional type evaluation can't result in String"
            return Some(new ConditionalNotBoolean(message))
          }
          case Some(MoneyType) => {
            val message = "Conditional type evaluation can't result in Money"
            return Some(new ConditionalNotBoolean(message))
          }
          case None => {
            val message = "Expression in conditional has to evaluate to type Boolean"
            return Some(new ConditionalNotBoolean(message))
          }
        }
      }
    }
    None
  }
}
