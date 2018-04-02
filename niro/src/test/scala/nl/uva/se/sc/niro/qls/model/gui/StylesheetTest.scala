package nl.uva.se.sc.niro.qls.model.gui

import cats.Semigroup
import cats.instances.all._
import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, BooleanType, IntegerType, StringType }
import nl.uva.se.sc.niro.qls.model.gui.style.{ Color, FontSize, Width }
import org.scalatest.WordSpec

class StylesheetTest extends WordSpec {

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

      val expected = newStyling
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

  "Merging style maps" should {
    "use right side if present" in {
      val currentStyles: Map[AnswerType, Styling] = Map(
        BooleanType -> Styling(
          color = Some(
            Color("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            Color("#DARKSIDE")
          ),
          fontSize = Some(
            FontSize(13)
          )
        )
      )
      val newStyles: Map[AnswerType, Styling] = Map(
        IntegerType -> Styling(
          color = Some(
            Color("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            Color("#THESUN")
          ),
          width = Some(
            Width(42)
          )
        )
      )

      val result = Semigroup[Map[AnswerType, Styling]].combine(currentStyles, newStyles)

      val expected = Map(
        BooleanType -> Styling(
          color = Some(
            Color("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            Color("#THESUN")
          ),
          fontSize = Some(
            FontSize(13)
          ),
          width = Some(
            Width(42)
          )
        ),
        IntegerType -> Styling(
          color = Some(
            Color("#CAFEBABE")
          )
        )
      )

      assert(expected == result)

    }
  }

}
