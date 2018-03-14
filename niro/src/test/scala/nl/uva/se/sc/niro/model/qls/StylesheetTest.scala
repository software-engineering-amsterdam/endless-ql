package nl.uva.se.sc.niro.model.qls

import org.scalatest.WordSpec

class StylesheetTest extends WordSpec {

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
                  Question("nonExisting")
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
                  Question("nonExisting")
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("countMeOut")
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
                  Question("letsStayTogether")
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
                  Question("notMe")
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether")
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
                  Question("letsStayTogether")
                ))
            )),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionOne",
                Seq(
                  Question("letsStayTogether")
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
                  Question("questionOne")
                ))
            ))
        ))
      val expected = Seq(Question("questionOne"))

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
                  Question("questionOne")
                )
              ),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionTwo")
                ))
            ))
        )
      )
      val expected = Seq(Question("questionOne"), Question("questionTwo"))

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
                  Question("questionOne"),
                  Question("questionTwo")
                )),
              Section(
                "SectionTwo",
                Seq(
                  Question("questionThree"),
                  Question("questionFour")
                ))
            )
          ),
          Page(
            "PageTwo",
            Seq(
              Section(
                "SectionThree",
                Seq(
                  Question("questionFive"),
                  Question("questionSix")
                ))
            ))
        )
      )
      val expected = Seq(
        Question("questionOne"),
        Question("questionTwo"),
        Question("questionThree"),
        Question("questionFour"),
        Question("questionFive"),
        Question("questionSix"))

      val actual = stylesheet.collectAllQuestions()

      assert(expected == actual)
    }
  }
}
