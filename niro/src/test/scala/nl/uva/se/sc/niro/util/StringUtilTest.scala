package nl.uva.se.sc.niro.util

import org.scalatest.WordSpec

class StringUtilTest extends WordSpec {
  "StringUtil" should {
    "not split all lowercase" in {
      val actual = StringUtil.addSpaceOnCaseChange("thisshouldnotbesplitted")

      val expected = "thisshouldnotbesplitted"

      assert(actual == expected)
    }

    "not split all lowercase with spaces" in {
      val actual = StringUtil.addSpaceOnCaseChange("this should not be splitted")

      val expected = "this should not be splitted"

      assert(actual == expected)
    }

    "not split all uppercase" in {
      val actual = StringUtil.addSpaceOnCaseChange("THISISNOTSPLITTED")

      val expected = "THISISNOTSPLITTED"

      assert(actual == expected)
    }

    "split on uppercase at start" in {
      val actual = StringUtil.addSpaceOnCaseChange("ThisShouldBeSplitted")

      val expected = "This Should Be Splitted"

      assert(actual == expected)
    }

    "split on uppercase after start" in {
      val actual = StringUtil.addSpaceOnCaseChange("thisShouldBeSplitted")

      val expected = "this Should Be Splitted"

      assert(actual == expected)
    }
  }
}
