package nl.uva.se.sc.niro.model.qls

import org.scalatest.WordSpec

class QLStylesheetTest extends WordSpec {

  "stylesheet collectAllQuestions" should {
    "find single question" in {
      val stylesheet = QLStylesheet(
        "SingleQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("questionOne", None)
                ),
                Map.empty)
            ),
            Map.empty)
        ),
        Map.empty)
      val expected = Seq(Question("questionOne", None))

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }

    "find two questions in two sections" in {
      val stylesheet = QLStylesheet(
        "TwoSections",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("questionOne", None)
                ),
                Map.empty),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionTwo", None)
                ),
                Map.empty)
            ),
            Map.empty
          )
        ),
        Map.empty
      )
      val expected = Seq(Question("questionOne", None), Question("questionTwo", None))

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }

    "find two questions in two pages" in {
      val stylesheet = QLStylesheet(
        "TwoSections",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("questionOne", None),
                  Question("questionTwo", None)
                ),
                Map.empty),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionThree", None),
                  Question("questionFour", None)
                ),
                Map.empty)
            ),
            Map.empty
          ),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionThree",
                Seq(
                  Question("questionFive", None),
                  Question("questionSix", None)
                ),
                Map.empty)
            ),
            Map.empty)
        ),
        Map.empty
      )
      val expected = Seq(
        Question("questionOne", None),
        Question("questionTwo", None),
        Question("questionThree", None),
        Question("questionFour", None),
        Question("questionFive", None),
        Question("questionSix", None)
      )

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }
  }
}
