import ql.models.ast.{ BooleanType, StringType, IntegerType }
import qls.models.ast._
import qls.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class QLSASTParserSpec extends FunSpec {
  describe("when parsing a QLS sheet") {
    it("should contain a question that is directly in a Page") {
      val qls =
        FormHelper.getQuestions(getClass.getResource("qls/parser/simple.qls"))

      qls should contain(Question(Identifier("didYouBuyAHouse"), None))
    }

    it("should contain two sections") {
      val qls =
        FormHelper.getSections(getClass.getResource("qls/parser/simple.qls"))

      val s1 = Section(StringValue("Buying"),
                       List(Question(Identifier("hasBoughtHouse"), None)))
      val s2 = Section(StringValue("Loaning"),
                       List(Question(Identifier("hasMaintLoan"), None)))

      qls should contain(s1)
      qls should contain(s2)
    }

    describe("should contain a question with advanced styling") {
      val formLoc = getClass.getResource("qls/parser/advanced_styling.qls")
      val question = FormHelper.getQuestion(formLoc, Identifier("hasMaintLoan"))
      val styling = question.flatMap(x => x.styling).getOrElse(List())

      it("getQuestion should have returned something") {
        question.flatMap(x => Some(succeed)).getOrElse(fail("no question found"))
      }

      it("should contain widthStyling") {
        styling should contain(WidthStyling(IntegerValue(400)))
      }

      it("should contain fontStyling") {
        styling should contain(FontStyling(StringValue("Arial")))
      }

      it("should contain fontSizeStyling") {
        styling should contain(FontSizeStyling(IntegerValue(14)))
      }

      it("should contain colorStyling") {
        styling should contain(ColorStyling(StringValue("#999999")))
      }

      it("should contain WidgetStyling") {
        val expected = WidgetStyling(
          SpinboxWidget(None)
        )

        styling should contain(WidgetStyling(SpinboxWidget(None)))
      }
    }

    describe("should contain a question with inline styling") {
      val formLoc = getClass.getResource("qls/parser/advanced_styling.qls")
      val question = FormHelper.getQuestion(formLoc, Identifier("hasBoughtHouse"))
      val styling = question.flatMap(x => x.styling).getOrElse(List())

      it("getQuestion should have returned something") {
        question.flatMap(x => Some(succeed)).getOrElse(fail("no question found"))
      }

      it("should contain widthStyling") {
        styling should contain(WidgetStyling(CheckboxWidget(None, List())))
      }
    }

    describe("should contain options when question has list with values") {
      val formLoc = getClass.getResource("qls/parser/advanced_styling.qls")
      val question = FormHelper.getQuestion(formLoc, Identifier("hasSoldHouse"))
      val styling = question.flatMap(x => x.styling).getOrElse(List())

      it("getQuestion should have returned something") {
        question.flatMap(x => Some(succeed)).getOrElse(fail("no question found"))
      }

      it("should contain widthStyling") {
        val expected = WidgetStyling(CheckboxWidget(Some(StringType),
          List(StringValue("first"), StringValue("second"))
        ))
        styling should contain(expected)
      }
    }
  }

  describe("parsing a QLS form containg a default decl") {
    val formLoc = getClass.getResource("qls/parser/default_simple.qls")
    val defaultDecls = FormHelper.getDefaultDecls(formLoc)

    it("should contain radio widget") {
      val expected = DefaultDecl(
        BooleanType,
        List(
          WidgetStyling(
            RadioWidget(Some(BooleanType), List(PolarValue("Yes"), PolarValue("No")))
        ))
      )
      defaultDecls should contain(expected)
    }

    it("should contain dropdown widget") {
      val expected = DefaultDecl(
        BooleanType,
        List(
          WidgetStyling(
            DropdownWidget(Some(BooleanType), List(BooleanValue(true), BooleanValue(false)))
        ))
      )
      defaultDecls should contain(expected)
    }

    it("should contain slider widget") {
      val expected = DefaultDecl(
        IntegerType,
        List(
          WidgetStyling(
            SliderWidget(Some(IntegerType), List(IntegerValue(1), IntegerValue(2))
          )
        ))
      )
      defaultDecls should contain(expected)
    }

    it("should contain text widget") {
      val expected = DefaultDecl(
        StringType,
        List(
          WidgetStyling(
            TextWidget(None)
          )
        )
      )
      defaultDecls should contain(expected)
    }
  }

  describe("should contain a defaultdecl with advanced styling") {
    val formLoc = getClass.getResource("qls/parser/default_advanced_styling.qls")
    val defaultDecl = FormHelper.getDefaultDecls(formLoc).head
    val styling = defaultDecl.styling

    it("defaultDecl should contain typedecl") {
      assert(defaultDecl.nodeType == StringType)
    }

    it("should contain widthStyling") {
      styling should contain(WidthStyling(IntegerValue(400)))
    }

    it("should contain fontStyling") {
      styling should contain(FontStyling(StringValue("Arial")))
    }

    it("should contain fontSizeStyling") {
      styling should contain(FontSizeStyling(IntegerValue(14)))
    }

    it("should contain colorStyling") {
      styling should contain(ColorStyling(StringValue("#999999")))
    }

    it("should contain WidgetStyling") {
      styling should contain(WidgetStyling(SpinboxWidget(None)))
    }
  }
}
