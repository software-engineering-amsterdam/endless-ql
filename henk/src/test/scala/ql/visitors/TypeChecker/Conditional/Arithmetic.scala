import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ArithmeticOpSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions/arithmetic"
  val validator = new ConditionalValidator()

  describe("containing valid not boolean expression in unary") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
    }
  }
}
