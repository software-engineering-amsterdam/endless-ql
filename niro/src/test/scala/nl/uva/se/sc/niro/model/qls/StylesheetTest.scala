package nl.uva.se.sc.niro.model.qls

import org.scalatest.WordSpec

class StylesheetTest extends WordSpec {
  "stylesheet" should {
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

      val actual: Seq[String] = stylesheet.getPageNamesWithQuestion("onlyTheLonely")

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

      val actual: Seq[String] = stylesheet.getPageNamesWithQuestion("onlyTheLonely")

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

      val actual = stylesheet.getPageNamesWithQuestion("letsStayTogether")

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

      val actual = stylesheet.getPageNamesWithQuestion("letsStayTogether")

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

      val actual = stylesheet.getPageNamesWithQuestion("letsStayTogether")

      assert(expected == actual)
    }
  }
}
