import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DuplicateLabelSpec extends FunSpec {
  val resourceDir = "ql/typechecking/duplicate_label"
  val validator = new DuplicateLabelValidator()

  describe("when form contains no duplicate labels") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should return true") {
      assert(validator.execute(form))
    }
  }

  describe("when form contains a single source mono duplicated label") {
    val filename = s"${resourceDir}/single_duplicate_label.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should return an option exception") {
      assert(!validator.execute(form))
    }

    it("getWarnings should return list containing one label") {
      validator.getWarnings match {
        case Some(list: List[String]) => {
          if(list.size == 1) {
            succeed
          } else {
            fail("list did not contain one element")
          }
        }
        case other => fail()
      }
    }
  }

  describe("when form contains single source multiple duplicated labels") {
    val filename = s"${resourceDir}/double_duplicate_label.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should return an option exception") {
      assert(!validator.execute(form))
    }

    it("getWarnings should return list containing two similiar labels") {
      validator.getWarnings match {
        case Some(list: List[String]) => {
          if(list.size == 2 && list.distinct.size == 1) {
            succeed
          } else {
            fail("list did not contain one element")
          }
        }
        case other => fail()
      }
    }
  }

  describe("when form contains multi source multiple duplicated labels") {
    val filename = s"${resourceDir}/multi_double_duplicate_label.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should return an option exception") {
      assert(!validator.execute(form))
    }

    it("getWarnings should return list containing two different labels") {
      validator.getWarnings match {
        case Some(list: List[String]) => {
          if(list.size == 2 && list.distinct.size == 2) {
            succeed
          } else {
            fail("list did not contain one element")
          }
        }
        case other => fail()
      }
    }
  }
}
