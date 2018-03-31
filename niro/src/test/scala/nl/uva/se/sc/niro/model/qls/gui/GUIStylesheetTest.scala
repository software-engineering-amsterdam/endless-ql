package nl.uva.se.sc.niro.model.qls.gui

import cats.Semigroup
import cats.instances.all._
import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, BooleanType, IntegerType, StringType }
import nl.uva.se.sc.niro.qls.model.gui.GUIStyling
import nl.uva.se.sc.niro.qls.model.gui.style.{ GUIColor, GUIFontSize, GUIWidth }
import org.scalatest.WordSpec

class GUIStylesheetTest extends WordSpec {

  "Merging styling" should {
    "map to default if default is added to default" in {
      val defaultStyling = GUIStyling()

      val result = defaultStyling ++ GUIStyling()

      val expected = GUIStyling()
      assert(expected == result)
    }

    "map to current if default is added to current" in {
      val defaultStyling = GUIStyling()
      val currentStyling = GUIStyling(color = Some(GUIColor("#010203")))

      val result = currentStyling ++ defaultStyling

      val expected = GUIStyling(color = Some(GUIColor("#010203")))
      assert(expected == result)
    }

    "map to new if new is added to default" in {
      val defaultStyling = GUIStyling()
      val newStyling = GUIStyling(color = Some(GUIColor("#010203")))

      val result = defaultStyling ++ newStyling

      val expected = newStyling
      assert(expected == result)
    }

    "map to new if new is added to other" in {
      val otherStyling = GUIStyling(color = Some(GUIColor("#CAFEBABE")))
      val newStyling = GUIStyling(color = Some(GUIColor("#010203")))

      val result = otherStyling ++ newStyling

      val expected = GUIStyling(color = Some(GUIColor("#010203")))
      assert(expected == result)
    }
  }

  "Merging style maps" should {
    "use right side if present" in {
      val currentStyles: Map[AnswerType, GUIStyling] = Map(
        BooleanType -> GUIStyling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> GUIStyling(
          color = Some(
            GUIColor("#DARKSIDE")
          ),
          fontSize = Some(
            GUIFontSize(13)
          )
        )
      )
      val newStyles: Map[AnswerType, GUIStyling] = Map(
        IntegerType -> GUIStyling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> GUIStyling(
          color = Some(
            GUIColor("#THESUN")
          ),
          width = Some(
            GUIWidth(42)
          )
        )
      )

      val result = Semigroup[Map[AnswerType, GUIStyling]].combine(currentStyles, newStyles)

      val expected = Map(
        BooleanType -> GUIStyling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        ),
        StringType -> GUIStyling(
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
        IntegerType -> GUIStyling(
          color = Some(
            GUIColor("#CAFEBABE")
          )
        )
      )

      assert(expected == result)

    }
  }

}
