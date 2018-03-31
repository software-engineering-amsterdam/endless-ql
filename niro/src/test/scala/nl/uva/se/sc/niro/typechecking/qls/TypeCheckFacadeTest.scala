package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.ql.model.ast.{ BooleanType, IntegerType, MoneyType }
import nl.uva.se.sc.niro.qls.model.ast.Styling
import nl.uva.se.sc.niro.qls.model.ast.style.{ CheckBox, ComboBox, Radio, SpinBox }
import org.scalatest.WordSpec

class TypeCheckFacadeTest extends WordSpec {
  "integer widget" should {
    "be compatible with default (none) styling" in {
      val actual = WidgetStyles.stylingIsCompatible(IntegerType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default spinbox styling" in {
      val actual = WidgetStyles.stylingIsCompatible(IntegerType, Styling(widgetType = Some(SpinBox(0, 2, 1))))
      val expected = true
      assert(actual == expected)
    }

    "not be compatible with radio styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(IntegerType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with checkbox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(IntegerType, Styling(widgetType = Some(CheckBox())))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with combobox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(IntegerType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = false
      assert(actual == expected)
    }
  }

  "boolean widget" should {
    "be compatible with default (none) styling" in {
      val actual = WidgetStyles.stylingIsCompatible(BooleanType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default checkbox styling" in {
      val actual = WidgetStyles.stylingIsCompatible(BooleanType, Styling(widgetType = Some(CheckBox())))
      val expected = true
      assert(actual == expected)
    }

    "be compatible with radio styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(BooleanType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = true
      assert(actual == expected)
    }

    "be compatible with combobox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(BooleanType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = true
      assert(actual == expected)
    }

    "not be compatible with spinbox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(BooleanType, Styling(widgetType = Some(SpinBox(0, 2, 1))))
      val expected = false
      assert(actual == expected)
    }
  }

  "money widget" should {
    "be compatible with default (none) styling" in {
      val actual = WidgetStyles.stylingIsCompatible(MoneyType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default checkbox styling" in {
      val actual = WidgetStyles.stylingIsCompatible(MoneyType, Styling(widgetType = Some(CheckBox())))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with radio styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(MoneyType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with combobox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(MoneyType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "be compatible with spinbox styling" in {
      val actual =
        WidgetStyles.stylingIsCompatible(MoneyType, Styling(widgetType = Some(SpinBox(0, 2, 1))))
      val expected = true
      assert(actual == expected)
    }
  }
}
