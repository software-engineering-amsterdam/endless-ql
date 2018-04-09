package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class ConditionalNotBoolean(label: String) extends Exception(label)

class ConditionalValidator extends BaseValidator {
  def execute(ast: Root): Unit = {
    val statements = FormCollector.getStatements(ast)
    statements
      .flatMap(StatementCollector.getIfStatements(_))
      .map(_.expression)
      .find(expr => {
        !ValidatorHelper.isBooleanType(expr, ast)
      })
      .map(expr => {
        val nodeType = ValidatorHelper
          .infereExpression(expr, ast)
          .getOrElse("None")

        val message = s"""
          Expression in conditional evaluated to '${nodeType}' while 'boolean'
          is expected
        """
        throw new ConditionalNotBoolean(message)
      })
  }
}
