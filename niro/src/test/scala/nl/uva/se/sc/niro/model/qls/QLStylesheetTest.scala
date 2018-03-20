package nl.uva.se.sc.niro.model.qls

import org.scalatest.WordSpec

class QLStylesheetTest extends WordSpec {

  "stylesheet collectPageNamesWithQuestion" should {
    "find no page for question" in {
      val stylesheet = QLStylesheet(
        "noMatchingQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("nonExisting", None)
                ))
            ))
        ))
      val expected = Seq.empty

      val actual: Seq[String] = stylesheet.collectPageNamesWithQuestion("onlyTheLonely")

      assert(expected == actual)
    }

    "find no pages for question" in {
      val stylesheet = QLStylesheet(
        "noMatchingQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("nonExisting", None)
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("countMeOut", None)
                ))
            ))
        )
      )
      val expected = Seq.empty

      val actual: Seq[String] = stylesheet.collectPageNamesWithQuestion("onlyTheLonely")

      assert(expected == actual)
    }

    "find single page for question" in {
      val stylesheet = QLStylesheet(
        "noMatchingQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether", None)
                ))
            ))
        ))
      val expected = Seq("PageOne")

      val actual = stylesheet.collectPageNamesWithQuestion("letsStayTogether")

      assert(expected == actual)
    }

    "find pages for question among other pages" in {
      val stylesheet = QLStylesheet(
        "noMatchingQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("notMe", None)
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether", None)
                ))
            ))
        )
      )
      val expected = Seq("PageTwo")

      val actual = stylesheet.collectPageNamesWithQuestion("letsStayTogether")

      assert(expected == actual)
    }

    "find multiple pages for question among other pages" in {
      val stylesheet = QLStylesheet(
        "noMatchingQuestion",
        Seq(
          Page(
            "PageOne",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether", None)
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether", None)
                ))
            ))
        )
      )
      val expected = Seq("PageOne", "PageTwo")

      val actual = stylesheet.collectPageNamesWithQuestion("letsStayTogether")

      assert(expected == actual)
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
                  Question("questionOne", None)
                ))
            ))
        ))
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
                )
              ),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionTwo", None)
                ))
            ))
        )
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
                )),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionThree", None),
                  Question("questionFour", None)
                ))
            )
          ),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionThree",
                Seq(
                  Question("questionFive", None),
                  Question("questionSix", None)
                ))
            ))
        )
      )
      val expected = Seq(
        Question("questionOne", None),
        Question("questionTwo", None),
        Question("questionThree", None),
        Question("questionFour", None),
        Question("questionFive", None),
        Question("questionSix", None))

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }
  }
}
