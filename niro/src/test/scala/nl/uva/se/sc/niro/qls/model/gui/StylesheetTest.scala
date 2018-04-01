package nl.uva.se.sc.niro.qls.model.gui

import cats.Semigroup
import cats.instances.all._
import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, BooleanType, IntegerType, StringType }
import nl.uva.se.sc.niro.qls.model.gui.style.{ GUIColor, GUIFontSize, GUIWidth }
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
      val currentStyling = Styling(color = Some(GUIColor("#010203")))

      val result = currentStyling ++ defaultStyling

      val expected = Styling(color = Some(GUIColor("#010203")))
      assert(expected == result)
    }

    "map to new if new is added to default" in {
      val defaultStyling = Styling()
      val newStyling = Styling(color = Some(GUIColor("#010203")))

      val result = defaultStyling ++ newStyling

      val expected = newStyling
      assert(expected == result)
    }

    "map to new if new is added to other" in {
      val otherStyling = Styling(color = Some(GUIColor("#CAFEBABE")))
      val newStyling = Styling(color = Some(GUIColor("#010203")))

      val result = otherStyling ++ newStyling

      val expected = Styling(color = Some(GUIColor("#010203")))
      assert(expected == result)
    }
  }

  "Merging style maps" should {
    "use right side if present" in {
      val currentStyles: Map[AnswerType, Styling] = Map(
        BooleanType -> Styling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            GUIColor("#DARKSIDE")
          ),
          fontSize = Some(
            GUIFontSize(13)
          )
        )
      )
      val newStyles: Map[AnswerType, Styling] = Map(
        IntegerType -> Styling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            GUIColor("#THESUN")
          ),
          width = Some(
            GUIWidth(42)
          )
        )
      )

      val result = Semigroup[Map[AnswerType, Styling]].combine(currentStyles, newStyles)

      val expected = Map(
        BooleanType -> Styling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> Styling(
          color = Some(
            GUIColor("#THESUN")
          ),
          fontSize = Some(
            GUIFontSize(13)
          ),
          width = Some(
            GUIWidth(42)
          )
        ),
        IntegerType -> Styling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        )
      )

      assert(expected == result)

    }
  }

}
