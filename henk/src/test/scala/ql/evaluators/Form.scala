import ql.models.ast._
import ql.collectors._
import ql.evaluators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class FormEvaluator extends FunSpec {
  val resourceDir = "ql/evaluators/symbol_table"

  def evaluatorFactory(location: String): SymbolTableEvaluator = {
    val form = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/${location}"))
    return new SymbolTableEvaluator(form)
  }

  describe("conditionals containing no identifiers") {
    it("should return 0") {
      val questions = evaluatorFactory("simple.ql").getQuestions
      questions should have length (1)
    }

    it("conditionals containing expression that can be evaluated should be traversed") {
      val questions = evaluatorFactory("conditional_true.ql").getQuestions
      questions should have length (2)
    }

    it("conditionals containing expression that can't be evaluated should not be returned") {
      val questions = evaluatorFactory("conditional_false.ql").getQuestions
      questions should have length (1)
    }

    it("conditionals containing expression that can't be evaluated should continue in false branche") {
      val questions = evaluatorFactory("conditional_false_branch.ql").getQuestions
      questions should have length (2)
    }
  }

  describe("conditionals containing identifiers") {
    it("default value should map to false") {
      val questions = evaluatorFactory("default_identifier.ql").getQuestions
      questions should have length (2)
    }

    it("conditionals containing expression that can be evaluated should be traversed") {
      val questions = evaluatorFactory("computated_identifier.ql").getQuestions
      questions should have length (2)
    }
  }
}
