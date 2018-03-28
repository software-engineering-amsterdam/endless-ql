package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.model.ql.{ BooleanType, IntegerType, MoneyType }
import nl.uva.se.sc.niro.model.qls._
import nl.uva.se.sc.niro.model.qls.style.{ CheckBox, ComboBox, Radio, SpinBox }
import org.scalatest.WordSpec

class QLSTypeCheckFacadeTest extends WordSpec {
  "integer widget" should {
    "be compatible with default (none) styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(IntegerType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default spinbox styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(IntegerType, Styling(widgetType = Some(SpinBox())))
      val expected = true
      assert(actual == expected)
    }

    "not be compatible with radio styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(IntegerType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with checkbox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(IntegerType, Styling(widgetType = Some(CheckBox())))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with combobox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(IntegerType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = false
      assert(actual == expected)
    }
  }

  "boolean widget" should {
    "be compatible with default (none) styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(BooleanType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default checkbox styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(BooleanType, Styling(widgetType = Some(CheckBox())))
      val expected = true
      assert(actual == expected)
    }

    "be compatible with radio styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(BooleanType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = true
      assert(actual == expected)
    }

    "be compatible with combobox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(BooleanType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = true
      assert(actual == expected)
    }

    "not be compatible with spinbox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(BooleanType, Styling(widgetType = Some(SpinBox())))
      val expected = false
      assert(actual == expected)
    }
  }

  "money widget" should {
    "be compatible with default (none) styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(MoneyType, Styling())
      val expected = true
      assert(actual == expected)
    }

    "be compatible with default checkbox styling" in {
      val actual = QLSTypeCheckFacade.stylingIsCompatible(MoneyType, Styling(widgetType = Some(CheckBox())))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with radio styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(MoneyType, Styling(widgetType = Some(Radio("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with combobox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(MoneyType, Styling(widgetType = Some(ComboBox("true", "false"))))
      val expected = false
      assert(actual == expected)
    }

    "not be compatible with spinbox styling" in {
      val actual =
        QLSTypeCheckFacade.stylingIsCompatible(MoneyType, Styling(widgetType = Some(SpinBox())))
      val expected = false
      assert(actual == expected)
    }
  }
}
