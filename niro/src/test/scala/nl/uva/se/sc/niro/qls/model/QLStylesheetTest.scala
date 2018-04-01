package nl.uva.se.sc.niro.qls.model

import nl.uva.se.sc.niro.qls.model.ast._
import nl.uva.se.sc.niro.qls.model.ast.style.Color
import org.scalatest.WordSpec

class QLStylesheetTest extends WordSpec {

  "Merging styling" should {
    "map to default if default is added to default" in {
      val defaultStyling = Styling()

      val result = defaultStyling ++ Styling()

      val expected = Styling()
      assert(expected == result)
    }

    "map to current if default is added to current" in {
      val defaultStyling = Styling()
      val currentStyling = Styling(color = Some(Color("#010203")))

      val result = currentStyling ++ defaultStyling

      val expected = Styling(color = Some(Color("#010203")))
      assert(expected == result)
    }

    "map to new if new is added to default" in {
      val defaultStyling = Styling()
      val newStyling = Styling(color = Some(Color("#010203")))

      val result = defaultStyling ++ newStyling

      val expected = Styling(color = Some(Color("#010203")))
      assert(expected == result)
    }

    "map to new if new is added to other" in {
      val otherStyling = Styling(color = Some(Color("#CAFEBABE")))
      val newStyling = Styling(color = Some(Color("#010203")))

      val result = otherStyling ++ newStyling

      val expected = Styling(color = Some(Color("#010203")))
      assert(expected == result)
    }
  }

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
                  Question("questionOne", Styling())
                ),
                Map.empty)
            ),
            Map.empty)
        ),
        Map.empty)
      val expected = Seq(Question("questionOne", Styling()))

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
                  Question("questionOne", Styling())
                ),
                Map.empty),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionTwo", Styling())
                ),
                Map.empty)
            ),
            Map.empty
          )
        ),
        Map.empty
      )
      val expected = Seq(Question("questionOne", Styling()), Question("questionTwo", Styling()))

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
                  Question("questionOne", Styling()),
                  Question("questionTwo", Styling())
                ),
                Map.empty),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionThree", Styling()),
                  Question("questionFour", Styling())
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
                  Question("questionFive", Styling()),
                  Question("questionSix", Styling())
                ),
                Map.empty)
            ),
            Map.empty)
        ),
        Map.empty
      )
      val expected = Seq(
        Question("questionOne", Styling()),
        Question("questionTwo", Styling()),
        Question("questionThree", Styling()),
        Question("questionFour", Styling()),
        Question("questionFive", Styling()),
        Question("questionSix", Styling())
      )

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }
  }
}
